package it.gabrielecabrini.kosh.core.parsing

import it.gabrielecabrini.kosh.COMMAND_REGISTRY

object CommandParser {

    fun parse(input: String): List<ParsedCommand> {
        return input.split("|").map { commandString ->
            parseSingleCommand(commandString.trim())
        }
    }

    private fun parseSingleCommand(commandString: String): ParsedCommand {
        val parts = commandString.split(" ")
        val name = parts[0]
        val args = mutableListOf<String>()
        var stdinRedirect: String? = null
        var stdoutRedirect: String? = null
        var stderrRedirect: String? = null
        var appendToStdout = false
        var appendToStderr = false

        var i = 1
        while (i < parts.size) {
            when (parts[i]) {
                ">" -> {
                    stdoutRedirect = parts.getOrNull(++i)
                    appendToStdout = false
                }

                ">>" -> {
                    stdoutRedirect = parts.getOrNull(++i)
                    appendToStdout = true
                }

                "2>" -> {
                    stderrRedirect = parts.getOrNull(++i)
                    appendToStderr = false
                }

                "2>>" -> {
                    stderrRedirect = parts.getOrNull(++i)
                    appendToStderr = true
                }

                "<" -> {
                    stdinRedirect = parts.getOrNull(++i)
                }

                else -> args.add(parts[i])
            }
            i++
        }

        return ParsedCommand(
            command = COMMAND_REGISTRY.getCommand(name),
            args = args,
            inputFile = stdinRedirect,
            outputFile = stdoutRedirect,
            appendToFile = appendToStdout,
            errorFile = stderrRedirect,
            appendToError = appendToStderr
        )
    }
}
