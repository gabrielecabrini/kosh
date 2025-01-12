package it.gabrielecabrini.kosh.core

import it.gabrielecabrini.kosh.COMMAND_REGISTRY

class Pipeline(private val commands: List<Pair<Command, List<String>>>) {
    companion object {
        fun parse(input: String): Pipeline {
            val commandStrings = input.split("|").map { it.trim() }
            val commands = commandStrings.map { commandString ->
                val parts = commandString.split(" ")
                val name = parts[0]
                val args = parts.drop(1)
                val command = COMMAND_REGISTRY.getCommand(name)
                command to args
            }
            return Pipeline(commands)
        }
    }

    fun execute(output: Appendable) {
        var intermediateInput = ""
        commands.forEachIndexed { index, (command, args) ->
            val intermediateOutput = StringBuilder()
            command.execute(args, input = intermediateInput, output = intermediateOutput)
            intermediateInput = intermediateOutput.toString()
            if (index == commands.lastIndex) {
                output.append(intermediateInput)
            }
        }
    }

}
