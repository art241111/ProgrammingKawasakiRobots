package com.github.polyKA.kawasakiControlLibrary.commands.command.move

import com.github.art241111.tcpClient.writer.SenderImp
import com.github.polyKA.kawasakiControlLibrary.commands.MoveCommand
import com.github.polyKA.kawasakiControlLibrary.commands.command.implementation.Command
import com.github.polyKA.kawasakiControlLibrary.coordinates.Coordinate

class MoveByCoordinate(private val command: Coordinate,
                       private val position : Double): Command {

    override fun run(sender: SenderImp) {
        sender.send(MoveCommand.MOVE_BY_COORDINATE.command
                + (command.ordinal + 1)
                + ";"
                + position.toInt())
    }
}