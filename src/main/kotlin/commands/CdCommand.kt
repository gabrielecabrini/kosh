package me.gabrielecabrini.commands

import me.gabrielecabrini.core.BuiltinCommand
import java.io.File

class CdCommand : BuiltinCommand() {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        val target = File(currentDirectory, args[0]).canonicalFile
        if (target.exists() && target.isDirectory) {
            changeWorkingDirectory(target.path)
        } else {
            throw RuntimeException("cd: ${args[0]}: No such file or directory")
        }
    }
}