package com.sathiai

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.widget.LinearLayout
import android.graphics.Color
import android.view.Gravity

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setBackgroundColor(Color.BLACK)

        val title = TextView(this)
        title.text = "SATHI AI"
        title.textSize = 32f
        title.setTextColor(Color.GREEN)
        title.gravity = Gravity.CENTER

        val subtitle = TextView(this)
        subtitle.text = "Namaste! Main Sathi AI hoon."
        subtitle.textSize = 18f
        subtitle.setTextColor(Color.WHITE)
        subtitle.gravity = Gravity.CENTER

        layout.addView(title)
        layout.addView(subtitle)

        setContentView(layout)
    }
}
