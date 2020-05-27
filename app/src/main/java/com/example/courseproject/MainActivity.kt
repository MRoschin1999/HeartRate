package com.example.courseproject

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TableLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rows = 6
        var cols = 7
        val linear = findViewById(R.id.basic_linear) as LinearLayout
        for (i in 0 until rows){
            val lin = LinearLayout(this)
            lin.setTag("row "+i)
            lin.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            linear.addView(lin)
            for (j in 0 until cols){
                val button = Button(this)
                button.setTag(i.toString()+j.toString())
                button.setBackgroundResource(R.drawable.circle_btn)
                lin.addView(button)
            }
        }
    }
}
