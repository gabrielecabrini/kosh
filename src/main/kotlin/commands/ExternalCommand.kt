package me.gabrielecabrini.commands

import me.gabrielecabrini.core.Command
import java.io.File

class ExternalCommand(private val commandName: String) : Command {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        try {
            val processBuilder = ProcessBuilder(commandName, *args.toTypedArray())
            processBuilder.directory(File(System.getProperty("user.dir")))
            processBuilder.redirectErrorStream(true)

            val process = processBuilder.start()

            if (input.isNotEmpty()) {
                process.outputStream.bufferedWriter().use { writer ->
                    writer.write(input)
                    writer.flush()
                }
            }

            process.inputStream.bufferedReader().useLines { lines ->
                lines.forEach { output.appendLine(it) }
            }

            process.waitFor()
        } catch (e: Exception) {
            throw RuntimeException("kosh: Unknown command: $commandName")
        }
    }
}
