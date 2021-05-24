package com.example.recorder.play

import androidx.lifecycle.ViewModel

class TrackViewModel(val trackRepo: TrackRepo):ViewModel() {
    fun getTracks()=trackRepo.getTracks()
}