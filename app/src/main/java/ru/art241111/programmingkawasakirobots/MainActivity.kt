package ru.art241111.programmingkawasakirobots

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.poluka.kControlLibrary.KRobot
import com.github.poluka.kControlLibrary.actions.move.MoveToPoint
import com.github.poluka.kControlLibrary.dsl.kProgram
import com.github.poluka.kControlLibrary.enity.TypeOfMovement
import com.github.poluka.kControlLibrary.enity.position.Position


class MainActivity : AppCompatActivity() {
    private val address = "192.168.56.1"
    private val port = 49152
    private val robot = KRobot()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun connect(view: View) {
        robot.connect(address, port)

        robot.setConnectRobotStatusObserver {
            Log.d("status_observe", it.toString())
        }

        robot.position.observe(this) {
            try {
                Log.d("position_handler", it.toString())
            } catch (e: Exception) {
                Log.d("position_handler", "error")
            }
        }
    }

    fun disconnect(view: View) {
        robot.disconnect()
    }

    fun move(view: View) {
        val home = MoveToPoint(TypeOfMovement.LMOVE, robot.homePosition)
        val startPosition = Position(220,515,32,90,-180,0)
        val arcPosition = Position(0,515,132,90,-180,0)
        val endPosition = Position(-220,515,32,103,-180,6)

        robot.run(kProgram{
            moveToPoint(TypeOfMovement.LMOVE, startPosition)
            moveToPoint(TypeOfMovement.JMOVE, arcPosition)
            this.signalOn(2150)
            moveToPoint(TypeOfMovement.LMOVE, endPosition)
            this.signalOff(2150)
//            moveByArc(arcPosition, endPosition)
        })
//        MOVETO;LMOVE;220;515;32;90;-180;0
//        CMOVE;0;515;132;90;-180;0;-220;515;32;103;-180;0
    }



    fun goHome(view: View) {
        robot.run(kProgram {
            moveToPoint(TypeOfMovement.LMOVE, robot.homePosition)
        })
    }
}
