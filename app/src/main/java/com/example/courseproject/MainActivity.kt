package com.example.courseproject

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TableLayout
import Observation
import Config

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var config:Config = Config(6,7,4)
        var observation:Observation = Observation(config)
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
                    }
                }
            }
        }
    }
}
