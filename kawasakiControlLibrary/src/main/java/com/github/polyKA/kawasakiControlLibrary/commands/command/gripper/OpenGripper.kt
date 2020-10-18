package com.github.polyKA.kawasakiControlLibrary.commands.command.gripper

import com.github.art241111.tcpClient.writer.SenderImp
import com.github.polyKA.kawasakiControlLibrary.commands.MoveCommand
import com.github.polyKA.kawasakiControlLibrary.commands.command.implementation.Command

class OpenGripper: Command {
    override fun run(sender: SenderImp) {
        sender.send(MoveCommand.OPEN_GRIPPER.command)
    }
}