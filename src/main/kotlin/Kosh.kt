package it.gabrielecabrini.kosh

import it.gabrielecabrini.kosh.completer.BuiltinCompleter
import it.gabrielecabrini.kosh.core.CommandRegistry
import it.gabrielecabrini.kosh.core.Pipeline
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReader
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder

val COMMAND_REGISTRY = CommandRegistry

fun main() {
    println("Welcome to Kosh - The Kotlin Shell!")

    val terminal: Terminal = TerminalBuilder.builder().system(true).build()
    val lineReader: LineReader = LineReaderBuilder.builder()
        .terminal(terminal)
        .completer(BuiltinCompleter())
        .build()

    while (true) {
        try {
            val input = lineReader.readLine("$ ").trim()
            if (input.isBlank()) continue

            try {
                val pipeline = Pipeline.parse(input)
                pipeline.execute(output = System.out)
            } catch (e: Exception) {
                println("${e.message}")
            }

        } catch (e: UserInterruptException) {
            println("^C")
        } catch (e: EndOfFileException) {
            println("Exiting Kosh. Goodbye!")
            break
        } catch (e: Exception) {
            println("${e.message}")
        }
    }

}