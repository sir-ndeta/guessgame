package com.example.guessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView

class done : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_done)




        val displayer = intent.getStringExtra(EXTRA_MESSAGE)
        val markreceiver = findViewById<TextView>(R.id.reciver)
        markreceiver.text= displayer
    }
}