package com.github.poluka.kControlLibrary.dsl

import com.github.poluka.kControlLibrary.actions.Command
import com.github.poluka.kControlLibrary.actions.gripper.CloseGripper
import com.github.poluka.kControlLibrary.actions.gripper.OpenGripper
import com.github.poluka.kControlLibrary.actions.move.MoveByCoordinate
import com.github.poluka.kControlLibrary.actions.move.MoveToPoint
import com.github.poluka.kControlLibrary.actions.program.Program
import com.github.poluka.kControlLibrary.enity.Coordinate
import com.github.poluka.kControlLibrary.enity.TypeOfMovement
import com.github.poluka.kControlLibrary.enity.position.Position

/**
 * DSL метод, за счет которого можно создавать свои программы.
 * @param commands - команды, которые нужно добавить в программу.
 */
fun program(commands: Commands.() -> Unit) = Commands().apply(commands).commands

/**
 * Класс, который позволяет добавлять команды в програму.
 * @author artem241120@gmail.com (Artem Gerasimov)
 */
class Commands: AddCommandToProgram() {
    fun openGripper() = addCommand(OpenGripper())
    fun closeGripper() = addCommand(CloseGripper())
    fun moveByCoordinate(coordinate: Coordinate, distance: Double)
            = addCommand(MoveByCoordinate(coordinate, distance))
    fun moveToPoint(typeOfMovement: TypeOfMovement, position: Position)
            = addCommand(MoveToPoint(typeOfMovement, position))

    /**
     * Если требуется добавить не реализованную команду или
     * сохраненную в переменную, то используйте add.
     * @param command - команда, которую требуется добавить в Program.
     */
    fun add(command: Command) = addCommand(command)
    fun add(command: Program) = addCommands(command.getAll())
}
