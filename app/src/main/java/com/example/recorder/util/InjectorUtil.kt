package com.example.recorder.util

import android.content.Context
import com.example.recorder.play.TrackRepo
import com.example.recorder.play.TrackViewModelProvider
import com.example.recorder.record.RecorderRepo
import com.example.recorder.record.RecorderViewModelProvider

object InjectorUtils{
    fun provideRecorderViewModelFactory():RecorderViewModelProvider{
        val recorderRepo=RecorderRepo.getInstance()
        return RecorderViewModelProvider(recorderRepo)
    }

    fun provideTrackViewModelFactory():TrackViewModelProvider{
        val trackRepo=TrackRepo.getInstance()
        return TrackViewModelProvider(trackRepo)
    }
}