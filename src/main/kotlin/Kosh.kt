package it.gabrielecabrini.kosh

import it.gabrielecabrini.kosh.core.CommandRegistry
import it.gabrielecabrini.kosh.core.Pipeline

val COMMAND_REGISTRY = CommandRegistry

fun main() {
    println("Welcome to Kosh - The Kotlin Shell!")

    while (true) {
        print("$ ")
        val input = readlnOrNull()?.trim() ?: break
        if (input.isBlank()) continue

        try {
            val pipeline = Pipeline.parse(input)
            pipeline.execute(output = System.out)
        } catch (e: Exception) {
            println("${e.message}")
        }

    }

}