package it.gabrielecabrini.kosh.command

import it.gabrielecabrini.kosh.core.BuiltinCommand
import java.io.File

class CdCommand : BuiltinCommand() {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        if (args.isEmpty()) {
            changeWorkingDirectory(System.getProperty("user.home"))
            return
        }
        val target = File(currentDirectory, args[0]).canonicalFile
        if (target.exists() && target.isDirectory) {
            changeWorkingDirectory(target.path)
        } else {
            throw RuntimeException("cd: ${args[0]}: No such file or directory")
        }
    }
}