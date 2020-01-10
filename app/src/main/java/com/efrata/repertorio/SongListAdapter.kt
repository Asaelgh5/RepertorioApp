package com.efrata.repertorio

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.efrata.repertorio.data.Song

class SongListAdapter(context: Context, private val mSongListArray: List<Song>) :  
    ArrayAdapter<Song>(context, R.layout.item_song, mSongListArray) {
    
    private val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var mListener: OnItemClickListener? = null
    
    interface OnItemClickListener {
        fun onItemClick(singers: ArrayList<SingerNoteModel>)
    }
    
    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
    
    private class SongListViewHolder(itemView: View) {
        val titleTV: TextView = itemView.findViewById(R.id.titleSong)
        val artistTV: TextView = itemView.findViewById(R.id.artistSong)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        
        val view: View
        val viewHolder: SongListViewHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_song, parent, false)
            viewHolder = SongListViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as SongListViewHolder
        }
        
        val currentSongItem = mSongListArray[position]
        view.setOnClickListener {
            mListener?.onItemClick(currentSongItem.singerNoteList)
        }
        viewHolder.titleTV.text = currentSongItem.title
        viewHolder.artistTV.text = currentSongItem.artist

        return view
    }

    override fun getItem(position: Int): Song {
        return mSongListArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mSongListArray.size
    }
}