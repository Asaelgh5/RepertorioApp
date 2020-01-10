package com.efrata.repertorio

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.efrata.repertorio.data.Song
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SongListScreen : AppCompatActivity() {

    private val songList = arrayListOf<Song>()
    private lateinit var songListAdapter: SongListAdapter
    private lateinit var songListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        songListView = findViewById(R.id.songListView)
        songListAdapter = SongListAdapter(this, songList)
        songListAdapter.setOnItemClickListener(object : SongListAdapter.OnItemClickListener {
            override fun onItemClick(singers: ArrayList<SingerNoteModel>) {
                for (singer in singers) {
                    Log.d("Singers", "Singer: ${singer.singerValue}, Note: ${singer.noteValue}")
                }
            }
        })
        songListView.adapter = songListAdapter

        val database = FirebaseDatabase.getInstance().getReference("songs")
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {

                if (p0.exists()) {
                    for (item in p0.children) {
                        Log.d("Snapshot", item.value.toString())
                        songList.add(item.getValue(Song::class.java)!!)
                    }
                    songListAdapter.notifyDataSetChanged()
                    /*songList.forEach { song ->
                        Log.d("Done", song.id)
                        Log.d("Done", song.title)
                    }*/
                }
            }
        })
    }
}
