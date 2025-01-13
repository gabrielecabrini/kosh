package it.gabrielecabrini.kosh.core.registry

import it.gabrielecabrini.kosh.command.*

object CommandRegistry {
    private val commands: MutableMap<String, Command> = mutableMapOf()

    init {
        register("echo", EchoCommand())
        register("cd", CdCommand())
        register("pwd", PwdCommand())
        register("type", TypeCommand())
        register("exit", ExitCommand())
    }

    fun register(name: String, command: Command) {
        commands[name] = command
    }

    fun getCommand(name: String): Command {
        return commands[name] ?: ExternalCommand(name)
    }

    fun getRegisteredCommands(): Map<String, Command> = commands

}
