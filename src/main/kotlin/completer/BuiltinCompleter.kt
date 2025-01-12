package it.gabrielecabrini.kosh.completer

import it.gabrielecabrini.kosh.COMMAND_REGISTRY
import org.jline.reader.Candidate
import org.jline.reader.Completer
import org.jline.reader.LineReader
import org.jline.reader.ParsedLine

class BuiltinCompleter : Completer {
    override fun complete(reader: LineReader, line: ParsedLine, candidates: MutableList<Candidate>) {
        val buffer = line.word()
        val completions = COMMAND_REGISTRY.getRegisteredCommands().keys.filter { it.startsWith(buffer) }
        completions.forEach { command ->
            candidates.add(Candidate(command))
        }
    }

}