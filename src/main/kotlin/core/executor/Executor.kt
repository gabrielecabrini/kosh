package it.gabrielecabrini.kosh.core.executor

import it.gabrielecabrini.kosh.core.parser.CommandNode
import it.gabrielecabrini.kosh.core.parser.Node
import it.gabrielecabrini.kosh.core.parser.PipeNode
import java.io.File
import java.io.InputStream
import java.io.OutputStream

class Executor {
    fun execute(node: Node) {
        when (node) {
            is CommandNode -> executeCommand(node)
            is PipeNode -> executePipe(node)
        }
    }

    private fun executeCommand(commandNode: CommandNode) {
        val processBuilder = ProcessBuilder(commandNode.command, *commandNode.args.toTypedArray())
        processBuilder.directory(File(System.getProperty("user.dir")))
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT)
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT)
        processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT)

        val process = processBuilder.start()

        process.waitFor()
    }

    private fun executePipe(node: PipeNode) {
        val leftCommand: CommandNode = node.left as CommandNode
        val rightCommand: CommandNode = node.right as CommandNode
        val workingDirectory = File(System.getProperty("user.dir"))

        val leftProcessBuilder = ProcessBuilder(leftCommand.command, *leftCommand.args.toTypedArray())
        leftProcessBuilder.directory(workingDirectory)
        val leftProcess = leftProcessBuilder.start()

        val rightProcessBuilder = ProcessBuilder(rightCommand.command, *rightCommand.args.toTypedArray())
        rightProcessBuilder.directory(workingDirectory)
        val rightProcess = rightProcessBuilder.start()

        leftProcess.inputStream.use { leftOutput ->
            rightProcess.outputStream.use { rightInput ->
                copyStream(leftOutput, rightInput)
            }
        }

        rightProcess.inputStream.bufferedReader().useLines { rightOutput ->
            rightOutput.forEach { line -> println(line) }
        }

        leftProcess.waitFor()
        rightProcess.waitFor()
    }

    private fun copyStream(input: InputStream, output: OutputStream) {
        input.copyTo(output)
    }

}