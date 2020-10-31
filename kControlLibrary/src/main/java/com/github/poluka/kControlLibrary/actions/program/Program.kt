package com.github.poluka.kControlLibrary.actions.program

import com.github.poluka.kControlLibrary.actions.Command
import com.github.poluka.kControlLibrary.actions.annotation.ExecutedOnTheRobot

@ExecutedOnTheRobot
class Program: Command {
    val commands = mutableListOf<Command>()

    override fun run(): String {
        var command = ""
        if(commands.isNotEmpty()){
            commands.forEach {
                command += it.run() + "%"
            }
        }
        return command
    }

    fun add(command: Command): Program{
        commands.add(command)
        return this
    }
}