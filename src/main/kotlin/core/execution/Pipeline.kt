package it.gabrielecabrini.kosh.core.execution

import it.gabrielecabrini.kosh.core.parsing.CommandParser
import it.gabrielecabrini.kosh.core.parsing.ParsedCommand

class Pipeline(private val commands: List<ParsedCommand>) {

    companion object {
        fun fromInput(input: String): Pipeline {
            val parsedCommands = CommandParser.parse(input)
            return Pipeline(parsedCommands)
        }
    }

    fun execute(output: Appendable) {
        var intermediateInput = ""

        commands.forEachIndexed { index, command ->
            val result = CommandExecutor.execute(command, intermediateInput)
            intermediateInput = result.output

            if (index == commands.lastIndex && command.outputFile == null) {
                output.append(result.output)
            }
        }
    }
}
