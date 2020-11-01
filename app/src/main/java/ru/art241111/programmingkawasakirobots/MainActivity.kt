package ru.art241111.programmingkawasakirobots

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.poluka.kControlLibrary.KRobot
import com.github.poluka.kControlLibrary.actions.move.MoveByCoordinate
import com.github.poluka.kControlLibrary.actions.move.MoveToPoint
import com.github.poluka.kControlLibrary.actions.program.Program
import com.github.poluka.kControlLibrary.dsl.program
import com.github.poluka.kControlLibrary.enity.Coordinate
import com.github.poluka.kControlLibrary.enity.TypeOfMovement


class MainActivity : AppCompatActivity() {
    private val address = "192.168.31.62"
    private val port = 49152
    private val robot = KRobot()

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
        val home = MoveToPoint(TypeOfMovement.LMOVE, robot.homePosition)

        val command = program {
                                        add(home)
                                        for (i in 0..30){
                                            if(i%2 == 0){
                                                moveByCoordinate(Coordinate.X, 10.0*i)
                                            } else{
                                                moveByCoordinate(Coordinate.X, -(10.0*i))
                                            }
                                            add(home)
                                        }
                                        add(home)
                                    }

        val program2 = program {
            add(command)
        }

        robot.run(program2)
    }
}
