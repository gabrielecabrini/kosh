package it.gabrielecabrini.kosh.core.parser

data class CommandNode(val command: String, val args: List<String>) : Node()