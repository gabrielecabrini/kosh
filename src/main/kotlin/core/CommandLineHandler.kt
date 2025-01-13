package it.gabrielecabrini.kosh.core

import it.gabrielecabrini.kosh.core.execution.Pipeline

class CommandLineHandler(private val output: Appendable) {

    fun handleInput(input: String) {
        try {
            if (input.isBlank()) return

            val pipeline = Pipeline.fromInput(input)
            pipeline.execute(output)

        } catch (e: Exception) {
            println("${e.message}")
        }
    }

}
