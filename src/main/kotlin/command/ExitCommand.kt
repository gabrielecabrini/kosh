package it.gabrielecabrini.kosh.command

import it.gabrielecabrini.kosh.core.BuiltinCommand
import kotlin.system.exitProcess

class ExitCommand : BuiltinCommand() {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        if (args.isEmpty()) {
            exitProcess(0)
        }

        val exitCode = args.firstOrNull()?.toIntOrNull() ?: run {
            output.appendLine("exit: ${args.firstOrNull()}: invalid integer")
            return
        }
        exitProcess(exitCode)
    }

}