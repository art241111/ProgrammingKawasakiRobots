package com.github.poluka.kControlLibrary.dsl

import com.github.poluka.kControlLibrary.actions.Command
import com.github.poluka.kControlLibrary.actions.program.Program

open class AddCommandToProgram(){
    val commands = Program()

    protected fun<T: Command> addCommand(command: T){
        commands.add(command)
    }
}