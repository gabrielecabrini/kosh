package it.gabrielecabrini.kosh.core.io

import java.io.File

object RedirectionHandler {

    fun readInput(inputFile: String?): String {
        return inputFile?.let { File(it).readText() } ?: ""
    }

    fun writeOutput(output: String, outputFile: String?, append: Boolean) {
        outputFile?.let { filePath ->
            val file = File(filePath)
            if (append) {
                file.appendText(output)
            } else {
                file.writeText(output)
            }
        }
    }
}
