package it.gabrielecabrini.kosh

import it.gabrielecabrini.kosh.completer.BuiltinCompleter
import it.gabrielecabrini.kosh.completer.ExecutablesCompleter
import it.gabrielecabrini.kosh.core.CommandLineHandler
import it.gabrielecabrini.kosh.core.registry.CommandRegistry
import org.jline.builtins.Completers.FileNameCompleter
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReader
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.reader.impl.completer.AggregateCompleter
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder

val COMMAND_REGISTRY = CommandRegistry

fun main() {
    println("Welcome to Kosh - The Kotlin Shell!")

    val terminal: Terminal = TerminalBuilder.builder().system(true).build()
    val lineReader: LineReader = LineReaderBuilder.builder()
        .appName("kosh")
        .terminal(terminal)
        .completer(AggregateCompleter(BuiltinCompleter(), ExecutablesCompleter(), FileNameCompleter()))
        .build()

    val commandLineHandler = CommandLineHandler(System.out)

    while (true) {
        try {
            val input = lineReader.readLine("$ ").trim()
            commandLineHandler.handleInput(input)
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
