package com.github.polyKA.kawasakiControlLibrary.commands.command.move

import com.github.art241111.tcpClient.writer.SenderImp
import com.github.polyKA.kawasakiControlLibrary.commands.MoveCommand
import com.github.polyKA.kawasakiControlLibrary.commands.command.implementation.Command

class MoveToPoint(private val typeOfMovement: String,
                  private val position: String): Command {

    override fun run(sender: SenderImp) {
        sender.send("${MoveCommand.MOVE_TO_POINT.command}$typeOfMovement;$position")
    }
}