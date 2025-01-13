package it.gabrielecabrini.kosh

import it.gabrielecabrini.kosh.completer.BuiltinCompleter
import it.gabrielecabrini.kosh.completer.ExecutablesCompleter
import it.gabrielecabrini.kosh.core.CommandLineHandler
import org.jline.builtins.Completers.FileNameCompleter
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReader
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.reader.impl.completer.AggregateCompleter
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import java.net.InetAddress

fun main(args: Array<String>) {
    println("Welcome to Kosh - The Kotlin Shell!")

    val hostname = InetAddress.getLocalHost().hostName
    val terminal: Terminal = TerminalBuilder.builder()
        .name("kosh")
        .system(true)
        .build()
    val lineReader: LineReader = LineReaderBuilder.builder()
        .appName("kosh")
        .terminal(terminal)
        .completer(AggregateCompleter(BuiltinCompleter(), ExecutablesCompleter(), FileNameCompleter()))
        .build()

    val commandLineHandler = CommandLineHandler(System.out)

    while (true) {
        val username = System.getProperty("user.name")
        try {
            val input = lineReader.readLine("$username@$hostname $ ").trim()
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
