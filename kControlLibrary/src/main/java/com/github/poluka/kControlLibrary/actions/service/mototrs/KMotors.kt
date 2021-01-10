package com.github.poluka.kControlLibrary.actions.service.mototrs

import com.github.poluka.kControlLibrary.actions.Command
import com.github.poluka.kControlLibrary.actions.annotation.ExecutedOnTheRobot
import com.github.poluka.kControlLibrary.actions.move.MoveToPoint
import com.github.poluka.kControlLibrary.actions.service.signal.Signal
import com.github.poluka.kControlLibrary.actions.service.signal.signalOn
import com.github.poluka.kControlLibrary.dsl.Program
import com.github.poluka.kControlLibrary.enity.TypeOfMovement
import com.github.poluka.kControlLibrary.enity.position.Position

private const val command = "MOTOR"
@ExecutedOnTheRobot
class KMotors(private val kMotorStatus: KMotorStatus): Command {
    override fun run(): String {
        return "$command;${kMotorStatus.name}"
    }
}

fun Program.motorOn() = doWithCommand(KMotors(KMotorStatus.ON))
//fun Program.motorOn() = signalOn(2130)// doWithCommand(Signal(2130))
fun Program.motorOff() = doWithCommand(KMotors(KMotorStatus.OFF))