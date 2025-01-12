package it.gabrielecabrini.kosh.core

import java.io.File

abstract class BuiltinCommand : Command {
    protected val currentDirectory: String
        get() = System.getProperty("user.dir")

    protected fun changeWorkingDirectory(directory: String) {
        System.setProperty("user.dir", File(directory).canonicalPath)
    }
}
