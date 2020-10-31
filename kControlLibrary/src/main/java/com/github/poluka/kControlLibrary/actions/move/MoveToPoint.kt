package com.github.poluka.kControlLibrary.actions.move

import com.github.poluka.kControlLibrary.actions.Command
import com.github.poluka.kControlLibrary.actions.annotation.ExecutedOnTheRobot
import com.github.poluka.kControlLibrary.enity.TypeOfMovement
import com.github.poluka.kControlLibrary.enity.position.Position

const val MOVE_TO_POINT = "MOVETO;"

@ExecutedOnTheRobot
data class MoveToPoint(private val typeOfMovement: TypeOfMovement = TypeOfMovement.LMOVE,
                           private val position: Position): Command {

    override fun run() = "${MOVE_TO_POINT}$typeOfMovement;$position"

}