package it.gabrielecabrini.kosh.core.lexer

class Lexer(private val input: String) {
    private var position = 0
    private var isFistWordInCommand = true

    fun tokenize(): List<Token> {
        val tokens = mutableListOf<Token>()
        while (position < input.length) {
            when (val char = input[position]) {
                ' ', '\t', '\r', '\n' -> position++
                '|' -> {
                    tokens.add(Token(TokenType.PIPE, "|"))
                    isFistWordInCommand = true
                    position++
                }

                ';' -> {
                    tokens.add(Token(TokenType.SEMICOLON, ";"))
                    isFistWordInCommand = true
                    position++
                }

                else -> {
                    val word = readWord()
                    tokens.add(classifyWord(word))
                }
            }
        }
        return tokens
    }


    private fun readWord(): String {
        val start = position
        while (position < input.length && input[position] !in " |\t\n|>") {
            position++
        }
        return input.substring(start, position)
    }

    private fun classifyWord(word: String): Token {
        return if (isFistWordInCommand) {
            isFistWordInCommand = false
            Token(TokenType.COMMAND, word)
        } else {
            Token(TokenType.ARGUMENT, word)
        }
    }

}