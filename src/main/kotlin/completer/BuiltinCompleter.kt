package it.gabrielecabrini.kosh.completer

import it.gabrielecabrini.kosh.core.registry.CommandRegistry
import org.jline.reader.Candidate
import org.jline.reader.Completer
import org.jline.reader.LineReader
import org.jline.reader.ParsedLine

class BuiltinCompleter : Completer {
    override fun complete(reader: LineReader, line: ParsedLine, candidates: MutableList<Candidate>) {
        if (line.wordIndex() != 0) return
        val buffer = line.word()
        val completions = CommandRegistry.getRegisteredCommands().keys.filter { it.startsWith(buffer) }
        completions.forEach { command ->
            candidates.add(Candidate(command))
        }
    }

}
