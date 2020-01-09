package com.efrata.repertorio.data

import com.efrata.repertorio.SingerNoteModel

class Song(
    val id: String,
    val title: String,
    val artist: String,
    val singerNoteList: ArrayList<SingerNoteModel>
)