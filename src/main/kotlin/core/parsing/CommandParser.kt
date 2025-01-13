package it.gabrielecabrini.kosh.core.parsing

import it.gabrielecabrini.kosh.COMMAND_REGISTRY

object CommandParser {

    fun parse(input: String): List<ParsedCommand> {
        return input.split("|").map { commandString ->
            val parts = commandString.trim().split(" ")
            val name = parts[0]
            val args = mutableListOf<String>()
            var inputFile: String? = null
            var outputFile: String? = null
            var appendToFile = false

            var i = 1
            while (i < parts.size) {
                when (parts[i]) {
                    ">" -> {
                        outputFile = parts.getOrNull(++i)
                        appendToFile = false
                    }

                    ">>" -> {
                        outputFile = parts.getOrNull(++i)
                        appendToFile = true
                    }

                    "<" -> {
                        inputFile = parts.getOrNull(++i)
                    }

                    else -> args.add(parts[i])
                }
                i++
            }

            ParsedCommand(
                command = COMMAND_REGISTRY.getCommand(name),
                args = args,
                inputFile = inputFile,
                outputFile = outputFile,
                appendToFile = appendToFile
            )
        }
    }
}
