package me.gabrielecabrini.commands

import me.gabrielecabrini.core.BuiltinCommand

class PwdCommand : BuiltinCommand() {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        output.appendLine(currentDirectory)
    }
}