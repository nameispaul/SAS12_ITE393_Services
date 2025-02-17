package com.ite393.sas12_services

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)

        startButton.setOnClickListener {
            startService(Intent(this, NewService::class.java))
        }

        stopButton.setOnClickListener {
            if (NewService.isMusicPlaying()) {
                stopService(Intent(this, NewService::class.java))
            } else {
                Toast.makeText(this, "No music is playing!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
