package it.gabrielecabrini.kosh.command

import it.gabrielecabrini.kosh.core.registry.BuiltinCommand
import java.io.File

class CdCommand : BuiltinCommand() {
    override fun execute(args: List<String>, input: String, output: Appendable) {
        if (args.isEmpty()) {
            changeWorkingDirectory(System.getProperty("user.home"))
            return
        }

        val targetPath = args[0]
        val target = if (targetPath.startsWith("/")) {
            File(targetPath).canonicalFile
        } else {
            File(currentDirectory, targetPath).canonicalFile
        }

        if (target.exists() && target.isDirectory) {
            changeWorkingDirectory(target.path)
        } else {
            throw RuntimeException("cd: ${target.path}: No such file or directory")
        }
    }
}
