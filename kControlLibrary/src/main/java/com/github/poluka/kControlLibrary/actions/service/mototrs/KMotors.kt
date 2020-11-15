package com.github.poluka.kControlLibrary.actions.service.mototrs

import com.github.poluka.kControlLibrary.actions.Command
import com.github.poluka.kControlLibrary.actions.annotation.ExecutedOnTheRobot

private const val command = "SERVICE"
@ExecutedOnTheRobot
class KMotors(private val kMotorStatus: KMotorStatus): Command {
    override fun run(): String = "$command;${kMotorStatus.name}"
}