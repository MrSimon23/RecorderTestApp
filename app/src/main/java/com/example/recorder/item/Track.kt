package com.example.recorder.item

import android.content.Context
import com.example.recorder.R
import com.example.recorder.play.TrackRepo
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.recording_layout.view.*


class Track(val title:String,val context: Context):Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.recordings_title_textview.text=title
        viewHolder.itemView.recording_image.setOnClickListener {
            TrackRepo.playTrack(context,title)
        }
    }

    override fun getLayout(): Int {
        return R.layout.recording_layout
    }
}