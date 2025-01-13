package it.gabrielecabrini.kosh.core.parsing

import it.gabrielecabrini.kosh.COMMAND_REGISTRY
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class CommandParserTest {

    @Test
    fun `should parse command with arguments`() {
        val command = COMMAND_REGISTRY.getCommand("echo")
        val input = "echo test arguments"

        val parsedCommands = CommandParser.parse(input)

        assertEquals(1, parsedCommands.size)
        val parsedCommand = parsedCommands[0]

        assertSame(command, parsedCommand.command)
        assertEquals(listOf("test", "arguments"), parsedCommand.args)
    }

}
