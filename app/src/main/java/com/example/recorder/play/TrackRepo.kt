package com.example.recorder.play

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.getSystemService
import java.io.File

class TrackRepo {
    companion object{
        @Volatile
        private var instance:TrackRepo?=null

        fun getInstance()= instance?: synchronized(this){
            instance?:TrackRepo().also { instance=it }
        }

        fun playTrack(context: Context,title:String){
            val path=Uri.parse(Environment.getExternalStorageState()+"/recorder/$title")
            val manager:AudioManager=context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            if (manager.isMusicActive){
                Toast.makeText(context,"Tsack is playing",Toast.LENGTH_SHORT).show()
            }else{
                val mediaPlayer:MediaPlayer?=MediaPlayer().apply {
                    setAudioStreamType(AudioManager.STREAM_MUSIC)
                    setDataSource(context,path)
                    prepare()
                    start()
                }
            }
        }
    }

    private val recorderDirectory=File(Environment.getExternalStorageState()+"/recorder/")
    private var file:ArrayList<String>?=null

    init {
        file= ArrayList<String>()
        getTrack()
    }

    private fun getTrack(){
        val files:Array<out File>?=recorderDirectory.listFiles()
        for (i in files!!){
            println(i.name)
            file?.add(i.name)
        }
    }

    fun getTracks()=file
}