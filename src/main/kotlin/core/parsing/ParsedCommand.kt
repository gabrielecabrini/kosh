package it.gabrielecabrini.kosh.core.parsing

import it.gabrielecabrini.kosh.core.registry.Command

data class ParsedCommand(
    val command: Command,
    val args: List<String>,
    val inputFile: String?,
    val outputFile: String?,
    val appendToFile: Boolean,
)
