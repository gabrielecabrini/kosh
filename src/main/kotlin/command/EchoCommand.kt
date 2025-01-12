package it.gabrielecabrini.kosh.command

import it.gabrielecabrini.kosh.core.BuiltinCommand

class EchoCommand : BuiltinCommand() {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        output.appendLine(args.joinToString(" "))
    }
}