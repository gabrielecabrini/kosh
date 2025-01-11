package me.gabrielecabrini

import me.gabrielecabrini.core.CommandRegistry
import me.gabrielecabrini.core.Pipeline

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
            println("Error: ${e.message}")
        }

    }

}