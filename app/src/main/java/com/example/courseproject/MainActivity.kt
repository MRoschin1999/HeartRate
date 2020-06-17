package com.example.courseproject

import Agent
import Config
import Observation
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var config:Config = Config(6,7,4)
        var observation:Observation = Observation(config)
        var agent:Agent = Agent(config);
        fun resetGame(){
            observation = Observation(config)
            for (i in 0 until 6) {
                for (j in 0 until 7) {
                    var buttonID: String = "button_" + i + j
                    var resID: Int = resources.getIdentifier(buttonID, "id", packageName)
                    var a = findViewById<Button>(resID)
                    a.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_btn));
                }
            }
        }
        val toast = Toast.makeText(
            applicationContext,
            "vot i vse",
            Toast.LENGTH_SHORT
        )
        for (i in 0 until 6){
            for (j in 0 until 7){
                var buttonID:String = "button_"+i+j
                var resID:Int = resources.getIdentifier(buttonID,"id",packageName)
                var a =findViewById<Button>(resID)
                a.setOnClickListener{
                    var res = observation.nextMove(config,j)
                    observation.board[res.row][res.col] = res.mark
                    buttonID ="button_"+res.row+res.col
                    resID= resources.getIdentifier(buttonID,"id",packageName)
                    a = findViewById(resID)
                    if (!res.win) {
                        if (res.mark == 1)
                            a.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_btn));
                        if (res.mark == 2)
                            a.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_btn));
                    } else{
                        if (res.mark == 1)
                            a.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_btn));
                        if (res.mark == 2)
                            a.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_btn));
                        toast.show()
                        resetGame()
                    }
                    res = observation.nextMove(config, agent.calculate_the_move(1, observation))
                    buttonID = "button_"+res.row+res.col
                    resID= resources.getIdentifier(buttonID,"id",packageName)
                    a = findViewById(resID)
                    var obs = observation.board
                    if (!res.win) {
                        if (res.mark == 1)
                            a.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_btn));
                        if (res.mark == 2)
                            a.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_btn));
                    } else{
                        if (res.mark == 1)
                            a.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_btn));
                        if (res.mark == 2)
                            a.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_btn));
                        toast.show()
                        resetGame()
                    }
                }
            }
        }
        var a = findViewById<Button>(resources.getIdentifier("reset","id",packageName))
        a.setOnClickListener{
            resetGame()
        }
    }
}
