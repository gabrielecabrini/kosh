package me.gabrielecabrini.commands

import me.gabrielecabrini.core.BuiltinCommand

class EchoCommand : BuiltinCommand() {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        output.appendLine(args.joinToString(" "))
    }
}