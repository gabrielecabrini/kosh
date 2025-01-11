package me.gabrielecabrini.core

import me.gabrielecabrini.commands.*

object CommandRegistry {
    private val commands: MutableMap<String, Command> = mutableMapOf()

    init {
        register("echo", EchoCommand())
        register("cd", CdCommand())
        register("pwd", PwdCommand())
        register("type", TypeCommand())
    }

    fun register(name: String, command: Command) {
        commands[name] = command
    }

    fun getCommand(name: String): Command {
        return commands[name] ?: ExternalCommand(name)
    }

}