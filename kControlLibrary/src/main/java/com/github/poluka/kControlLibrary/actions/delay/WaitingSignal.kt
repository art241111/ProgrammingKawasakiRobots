package com.github.poluka.kControlLibrary.actions.delay

import com.github.poluka.kControlLibrary.actions.Command
import com.github.poluka.kControlLibrary.actions.annotation.ExecutedOnTheRobot

/**
 *
 */
private const val command = "SERVICE;WSIGNAL"
@ExecutedOnTheRobot
class WaitingSignal(private val signal: Int): Command {
    override fun run(): String = "$command;$signal"
}