package com.example.recorder.record

import android.annotation.SuppressLint
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import java.io.File
import java.io.IOException
import java.lang.IllegalStateException
import java.util.*

class RecorderRepo {

    companion object{
        private var instance:RecorderRepo?=null

        fun getInstance()= instance?: synchronized(this){
            instance?:RecorderRepo().also { instance=it }
        }
    }
    private var output:String?=null
    private var mr:MediaRecorder?=null
    private val dir:File=File(Environment.getExternalStorageState()+"/recorder/")

    private var recordingTime:Long=0
    private var timer=Timer()
    private val recordingTimeString=MutableLiveData<String>()

    init {
        try {
            val recorderDirectory=File(Environment.getExternalStorageState()+"/recorder/")
            recorderDirectory.mkdirs()
        }catch (e:IOException){
            e.printStackTrace()
        }

        if (dir.exists()){
            val count=dir.listFiles().size
            output=Environment.getExternalStorageState()+"/recorder/recording"+count+".mp3"
        }

        mr = MediaRecorder()
        mr?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mr?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mr?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mr?.setOutputFile(output)
    }

    @SuppressLint("RestrictedApi")
    fun startRecording(){
        try {
            println("Recording start")
            mr?.prepare()
            mr?.start()
            startTimer()
        }catch (e:IllegalStateException){
            e.printStackTrace()
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    @SuppressLint("RestrictedApi")
    fun stopRecording(){
        mr?.stop()
        mr?.release()
        stopTimer()
        resetTimer()
        initRecorder()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    fun pauseRecording(){
      stopTimer()
      mr?.pause()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    fun resumeRecording(){
        timer= Timer()
        startTimer()
        mr?.resume()
    }

    private fun initRecorder(){
        mr= MediaRecorder()
        if (dir.exists()){
            val count=dir.listFiles().size
            output=Environment.getExternalStorageState()+"/recorder/recording"+count+".mp3"
        }
        mr?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mr?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mr?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mr?.setOutputFile(output)
    }

    private fun startTimer(){
        timer.scheduleAtFixedRate(object :TimerTask(){
            override fun run() {
                recordingTime+=1
                updateDisplay()
            }
        },1000,1000)
    }

    private fun stopTimer(){
        timer.cancel()
    }

    private fun resetTimer(){
        timer.cancel()
        recordingTime=0
        recordingTimeString.postValue("00:00")
    }

    private fun updateDisplay(){
        val min=recordingTime/(60)
        val sec=recordingTime%60
        val str= String.format("%d:%02d",min,sec)
        recordingTimeString.postValue(str)
    }

    fun getRTime()=recordingTimeString
}