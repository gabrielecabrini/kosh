package it.gabrielecabrini.kosh.core.parser

import it.gabrielecabrini.kosh.core.lexer.Token
import it.gabrielecabrini.kosh.core.lexer.TokenType

class Parser(private val tokens: List<Token>) {
    private var position = 0

    fun parse(): Node {
        return parsePipe()
    }

    private fun parsePipe(): Node {
        var left: Node = parseCommand()
        while (match(TokenType.PIPE)) {
            val right = parseCommand()
            left = PipeNode(left, right)
        }
        return left
    }

    private fun parseCommand(): CommandNode {
        val command = consume(TokenType.COMMAND).value
        val args = mutableListOf<String>()
        while (peek()?.type == TokenType.ARGUMENT) {
            args.add(consume(TokenType.ARGUMENT).value)
        }
        return CommandNode(command, args)
    }

    private fun match(type: TokenType): Boolean {
        if (peek()?.type == type) {
            position++
            return true
        }
        return false
    }

    private fun consume(type: TokenType): Token {
        val token = peek()
        if (token?.type == type) {
            position++
            return token
        }
        throw IllegalArgumentException("Expected $type but found ${token?.type}")
    }

    private fun peek(): Token? = if (position < tokens.size) tokens[position] else null

}