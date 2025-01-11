package me.gabrielecabrini.core

interface Command {

    /**
     * Executes the command with arguments and input/output for pipelining
     * @param args Command arguments
     * @param input Input stream (can be used for pipes)
     * @param output Output stream
     */
    fun execute(args: List<String>, input: String = "", output: Appendable = System.out)

}