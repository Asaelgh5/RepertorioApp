package com.efrata.repertorio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class NewSong : AppCompatActivity() {

    private lateinit var title: TextInputEditText
    private lateinit var artist: TextInputEditText
    private lateinit var keyNote: TextInputEditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_song)

        title = findViewById(R.id.title)
        artist = findViewById(R.id.artist)
        keyNote = findViewById(R.id.keyNote)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val titleStr = title.text
            val artistStr = artist.text
            val keyNoteStr = keyNote.text

            Log.d("save", titleStr.toString())
            //if (titleStr. )
        }
    }
}
