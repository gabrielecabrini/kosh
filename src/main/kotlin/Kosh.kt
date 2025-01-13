package it.gabrielecabrini.kosh

import it.gabrielecabrini.kosh.core.executor.Executor
import it.gabrielecabrini.kosh.core.lexer.Lexer
import it.gabrielecabrini.kosh.core.parser.Parser
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReader
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import java.net.InetAddress

fun main(args: Array<String>) {
    println("Welcome to Kosh (v${BuildConfig.APP_VERSION}) - The Kotlin Shell!")

    val hostname = InetAddress.getLocalHost().hostName
    val terminal: Terminal = TerminalBuilder.builder()
        .name("kosh")
        .system(true)
        .build()
    val lineReader: LineReader = LineReaderBuilder.builder()
        .appName("kosh")
        .terminal(terminal)
        //.completer(AggregateCompleter(BuiltinCompleter(), ExecutablesCompleter(), FileNameCompleter()))
        .build()

    while (true) {
        val username = System.getProperty("user.name")
        try {
            val input = lineReader.readLine("$username@$hostname $ ").trim()
            val lexer = Lexer(input)
            val tokens = lexer.tokenize()

            val ast = Parser(tokens).parse()

            val executor = Executor()
            executor.execute(ast)

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
