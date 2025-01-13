package it.gabrielecabrini.kosh.command

import it.gabrielecabrini.kosh.core.registry.BuiltinCommand
import it.gabrielecabrini.kosh.core.registry.CommandRegistry
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.name

class TypeCommand : BuiltinCommand() {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        if (args.isEmpty()) {
            return
        }

        val target = args[0]
        if (CommandRegistry.getCommand(target) is BuiltinCommand) {
            output.appendLine("$target is a builtin")
            return
        }

        System
            .getenv("PATH")
            .split(":")
            .stream()
            .map { Path.of(it) }
            .filter { Files.exists(it) }
            .flatMap { Files.list(it) }
            .toList()
            .firstOrNull { it.name == target }
            .let {
                if (it != null) println("$target is $it")
                else println("type: Could not found '$target'")

            }

    }
}
