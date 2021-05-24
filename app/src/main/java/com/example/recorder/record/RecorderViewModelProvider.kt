package com.example.recorder.record

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.content.Context

class RecorderViewModelProvider(val recorderRepo: RecorderRepo):ViewModelProvider.NewInstanceFactory(){
    @RequiresApi(Build.VERSION_CODES.N)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecorderViewModel(recorderRepo) as T
    }
}