package com.efrata.repertorio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.efrata.repertorio.data.Song
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class NewSong : AppCompatActivity() {

    private lateinit var title: TextInputEditText
    private lateinit var artist: TextInputEditText

    private lateinit var addButton: ImageButton
    private lateinit var saveButton: Button

//    private val singerList: ArrayList<TextInputEditText> = ArrayList()
//    private val noteList: ArrayList<TextInputEditText> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: SingerNoteAdapter
    private val singerNoteList: ArrayList<SingerNoteModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_song)

        title = findViewById(R.id.title)
        artist = findViewById(R.id.artist)
        addButton = findViewById(R.id.addButton)
        saveButton = findViewById(R.id.saveButton)
        recyclerView = findViewById(R.id.recyclerView)

        val newModel = SingerNoteModel("", "")
        singerNoteList.add(newModel)
        customAdapter = SingerNoteAdapter(singerNoteList)

        // Configurations
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)
        customAdapter.setOnItemClickListener(object : SingerNoteAdapter.OnItemClickListener {
            override fun onRemoveItem(position: Int) {
                removeItem(position)
            }
        })

        addButton.setOnClickListener {
            val addModel = SingerNoteModel("", "")
            singerNoteList.add(addModel)
            customAdapter.notifyItemInserted(singerNoteList.size.minus(1))
        }

        saveButton.setOnClickListener {
            val titleStr = title.text.toString().trim()
            val artistStr = artist.text.toString()
//            val keyNoteStr = keyNote.text.toString()

            var saveDB =  when {
                titleStr.isEmpty() -> {
                    title.error = "Ingrese el título de la canción"
                    false
                }
                artistStr.isEmpty() -> {
                    artist.error = "Ingrese el artísta de la canción"
                    false
                }
                else -> true
            }

            singerNoteList.forEach {model ->
                if (model.singerValue == "" || model.noteValue == "")
                    saveDB = false
            }

            if (!saveDB) {
                Toast.makeText(this, "Se necesitan llenar todos los campos", Toast.LENGTH_SHORT).show()
            } else if (singerNoteList.size == 0){
                Toast.makeText(this, "Debes agregar al menos a un cantante", Toast.LENGTH_SHORT).show()
            } else {
                val ref = FirebaseDatabase.getInstance().getReference("songs")
                val songID = ref.push().key

                val newSong = Song(songID!!, titleStr, artistStr, singerNoteList)
                ref.child(songID).setValue(newSong).addOnCompleteListener {
                    Toast.makeText(this, "Canción guardada exitosamente", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun removeItem(position: Int) {
        singerNoteList.removeAt(position)
        customAdapter.notifyItemRemoved(position)
    }
}
