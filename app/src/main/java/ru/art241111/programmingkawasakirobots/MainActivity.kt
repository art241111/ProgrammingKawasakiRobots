package ru.art241111.programmingkawasakirobots

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.polyKA.kawasakiControlLibrary.KawasakiRobot


class MainActivity : AppCompatActivity() {
    private val address = "192.168.31.62"
    private val port = 49152
    private val robot = KawasakiRobot()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun connect(view: View) {
        robot.connect(address, port)

        robot.position.observe(this, {
            try {
                Log.d("position_handler", it.toString())
            } catch (e: Exception) {
                Log.d("position_handler", "error")
            }
        })

        robot.statusRobot.observe(this, {
            Log.d("status_observe", it.toString())
        })
    }

    fun disconnect(view: View) {
        robot.disconnect()
    }

    fun move(view: View) {
//        robot.sendCommand("MOVE;BASE;1;-100")
//        val program = Program()
//        program.commands.add(MoveByCoordinate(Coordinate.X, 100.0))
//        program.commands.add(MoveByCoordinate(Coordinate.X, -100.0))
//
//        robot.move.runProgram(program)
        robot.homePosition.setHomePosition(0.0, 90.0, 0.0, 90.0, 0.0, 0.0)

    }
}