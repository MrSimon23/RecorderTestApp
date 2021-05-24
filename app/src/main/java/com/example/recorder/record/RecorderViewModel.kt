package com.example.recorder.record

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.recorder.util.RecorderState

@RequiresApi(Build.VERSION_CODES.N)
class RecorderViewModel(val recorderRepo: RecorderRepo):ViewModel(){
    var recorderState:RecorderState=RecorderState.Stopped

    fun startRecording()=recorderRepo.startRecording()
    fun stopRecording()=recorderRepo.stopRecording()
    fun pauseRecording()=recorderRepo.pauseRecording()
    fun resumeRecording()=recorderRepo.resumeRecording()
    fun getRTime()=recorderRepo.getRTime()
}