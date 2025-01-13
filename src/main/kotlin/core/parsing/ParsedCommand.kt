package it.gabrielecabrini.kosh.core.parsing

import it.gabrielecabrini.kosh.core.registry.Command

data class ParsedCommand(
    val command: Command,
    val args: List<String>,
    val inputFile: String? = null,
    val outputFile: String? = null,
    val appendToFile: Boolean = false,
    val errorFile: String? = null,
    val appendToError: Boolean = false
)
