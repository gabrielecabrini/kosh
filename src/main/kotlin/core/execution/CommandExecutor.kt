package it.gabrielecabrini.kosh.core.execution

import it.gabrielecabrini.kosh.core.parsing.ParsedCommand
import it.gabrielecabrini.kosh.core.io.RedirectionHandler

object CommandExecutor {

    data class ExecutionResult(val output: String)

    fun execute(command: ParsedCommand, input: String): ExecutionResult {
        val inputData = RedirectionHandler.readInput(command.inputFile) + input
        val outputBuilder = StringBuilder()

        command.command.execute(
            args = command.args,
            input = inputData,
            output = outputBuilder
        )

        val output = outputBuilder.toString()

        RedirectionHandler.writeOutput(output, command.outputFile, command.appendToFile)

        return ExecutionResult(output)
    }
}

