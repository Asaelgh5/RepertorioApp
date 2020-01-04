package com.efrata.repertorio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var newButton: Button
    private lateinit var showButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newButton = findViewById(R.id.addSong)
        showButton = findViewById(R.id.showSongs)

        newButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewSong::class.java)
            startActivity(intent)
        }
    }
}
