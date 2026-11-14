package com.sathiai

import android.app.Activity
import android.os.Bundle
import android.widget.*
import android.graphics.Color
import android.view.Gravity
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : Activity() {

    private lateinit var voice: VoiceAssistant
    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        voice = VoiceAssistant(this)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setPadding(30, 30, 30, 30)
        layout.setBackgroundColor(Color.BLACK)

        val title = TextView(this)
        title.text = "SATHI AI"
        title.textSize = 32f
        title.setTextColor(Color.GREEN)

        status = TextView(this)
        status.text = "Namaste! Main Sathi AI hoon."
        status.textSize = 18f
        status.setTextColor(Color.WHITE)

        val button = Button(this)
        button.text = "🎤 Bolo Sathi"

        button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    101
                )
            } else {
                status.text = "Main sun raha hoon..."
                voice.startListening { result ->
                    status.text = result
                    voice.speak("Aapne kaha $result")
                }
            }
        }

        layout.addView(title)
        layout.addView(status)
        layout.addView(button)

        setContentView(layout)
    }

    override fun onDestroy() {
        voice.stop()
        super.onDestroy()
    }
}
