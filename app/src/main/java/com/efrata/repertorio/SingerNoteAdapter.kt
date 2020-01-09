package com.efrata.repertorio

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText


class SingerNoteAdapter(private val mSingerNoteList: ArrayList<SingerNoteModel>): RecyclerView.Adapter<SingerNoteAdapter.TextViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onRemoveItem(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    class TextViewHolder(itemView: View, listener: OnItemClickListener, singerNoteList: ArrayList<SingerNoteModel>): RecyclerView.ViewHolder(itemView) {
        val singerEditText: TextInputEditText = itemView.findViewById(R.id.singer)
        val noteEditText: TextInputEditText = itemView.findViewById(R.id.keyNote)
        private val removeButton: ImageButton = itemView.findViewById(R.id.removeButton)

        init {
            singerEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    singerNoteList[adapterPosition].singerValue = (singerEditText.text.toString())
                }
                override fun afterTextChanged(editable: Editable) {}
            })

            noteEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    singerNoteList[adapterPosition].noteValue = (noteEditText.text.toString())
                }
                override fun afterTextChanged(editable: Editable) {}
            })

            removeButton.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onRemoveItem(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_singer_note, parent, false) as View

        return TextViewHolder(v, mListener!!, mSingerNoteList)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.singerEditText.setText(mSingerNoteList[position].singerValue)
        holder.noteEditText.setText(mSingerNoteList[position].noteValue)
    }

    override fun getItemCount(): Int {
        return mSingerNoteList.size
    }
}