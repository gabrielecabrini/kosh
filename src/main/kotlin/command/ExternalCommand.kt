package it.gabrielecabrini.kosh.command

import it.gabrielecabrini.kosh.core.Command
import java.io.File

class ExternalCommand(private val commandName: String) : Command {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        try {
            val processBuilder = ProcessBuilder(commandName, *args.toTypedArray())
            processBuilder.directory(File(System.getProperty("user.dir")))
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT)
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT)
            processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT)

            val process = processBuilder.start()

            // not interactive program and requires initial input
            if (input.isNotEmpty()) {
                process.outputStream.bufferedWriter().use { writer ->
                    writer.write(input)
                    writer.flush()
                }
            }

            process.waitFor()
        } catch (e: Exception) {
            throw RuntimeException("kosh: Unknown command: $commandName")
        }
    }
}
