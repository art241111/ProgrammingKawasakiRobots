package ru.art241111.programmingkawasakirobots

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.poluka.kControlLibrary.KRobot
import com.github.poluka.kControlLibrary.actions.move.MoveToPoint
import com.github.poluka.kControlLibrary.actions.move.move
import com.github.poluka.kControlLibrary.actions.move.moveByCoordinate
import com.github.poluka.kControlLibrary.actions.move.moveToPoint
import com.github.poluka.kControlLibrary.actions.service.mototrs.motorOn
import com.github.poluka.kControlLibrary.actions.service.signal.signalOn
import com.github.poluka.kControlLibrary.dsl.kProgram
import com.github.poluka.kControlLibrary.enity.Coordinate
import com.github.poluka.kControlLibrary.enity.TypeOfMovement
import com.github.poluka.kControlLibrary.enity.position.Position


class MainActivity : AppCompatActivity() {
    private val address = "192.168.31.63"
    private val port = 49152
    private val robot = KRobot()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun connect(view: View) {

        robot.setConnectRobotStatusObserver {
            Log.d("status_observe", it.toString())
        }

        robot.setPositionObserver {
            try {
                Log.d("position_handler", it.toString())
            } catch (e: Exception) {
                Log.d("position_handler", "error")
            }
        }
        robot.connect(address, port)

        robot.runWhenConnect(
                kProgram {
                    motorOn()
                    moveToPoint(TypeOfMovement.LMOVE,robot.homePosition)
                }
        )
    }

    fun disconnect(view: View) {
        robot.disconnect()
    }

    fun move(view: View) {
        val home = MoveToPoint(TypeOfMovement.LMOVE, robot.homePosition)
        val startPosition = Position(220,515,32,90,-180,0)
        val arcPosition = Position(0,515,132,90,-180,0)
        val endPosition = Position(-220,515,32,103,-180,6)

        robot.run(
                kProgram {
//                    moveToPoint(TypeOfMovement.LMOVE, startPosition)
//                    moveToPoint(TypeOfMovement.JMOVE, arcPosition)
//                    moveToPoint(TypeOfMovement.LMOVE, endPosition)
                    add(home)
                    move(x = 300.0, y= 100.0, z = 100.0)
                })
    }



    fun goHome(view: View) {
        robot.run(kProgram {
            moveToPoint(TypeOfMovement.LMOVE, robot.homePosition)
        })
    }
}
