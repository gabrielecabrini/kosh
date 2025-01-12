package it.gabrielecabrini.kosh.command

import it.gabrielecabrini.kosh.core.BuiltinCommand

class PwdCommand : BuiltinCommand() {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        output.appendLine(currentDirectory)
    }
}