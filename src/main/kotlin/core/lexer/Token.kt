package it.gabrielecabrini.kosh.core.lexer

data class Token(
    val type: TokenType,
    val value: String,
)
