package com.example.recorder.play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recorder.record.RecorderRepo
import com.example.recorder.record.RecorderViewModel

class TrackViewModelProvider(val trackRepo: TrackRepo):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrackViewModel(trackRepo)as T
    }
}