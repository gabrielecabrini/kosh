package it.gabrielecabrini.kosh.completer

import org.jline.reader.Candidate
import org.jline.reader.Completer
import org.jline.reader.LineReader
import org.jline.reader.ParsedLine
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class ExecutablesCompleter : Completer {

    private var executables = listOf<String>()

    init {
        executables = System.getenv("PATH")
            .split(File.pathSeparator)
            .asSequence()
            .map { Path.of(it) }
            .filter { Files.exists(it) }
            .flatMap { dir ->
                Files.list(dir)
                    .filter { Files.isExecutable(it) } // allow only executable files
                    .map { file -> file.fileName.toString() }
                    .toList()
                    .asSequence()
            }
            .toList()
    }

    override fun complete(reader: LineReader, line: ParsedLine, candidates: MutableList<Candidate>) {
        if (line.wordIndex() != 0) {
            return;
        }
        val buffer = line.word()
        val completions = executables.filter { it.startsWith(buffer) }
        completions.forEach { executable ->
            candidates.add(Candidate(executable))
        }
    }

}
