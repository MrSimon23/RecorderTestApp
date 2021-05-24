package com.example.recorder

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.util.jar.Manifest
import com.example.recorder.record.RecorderViewModel
import com.example.recorder.util.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var viewModel:RecorderViewModel?=null
    private lateinit var button_start:Button
    private lateinit var button_pause:Button
    private lateinit var button_stop: Button
    private lateinit var button_resume:Button
    private lateinit var button_fab:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkNeededPermissions()
        initUI()
        if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions, 0)
        }

        button_start.setOnClickListener {
            startRecording()
        }

        button_stop.setOnClickListener {
            stopRecording()
        }

        button_pause.setOnClickListener {
            pauseRecording()
        }

        button_resume.setOnClickListener{
            resumeRecording()
        }
    }

    private fun checkNeededPermissions(){
        println("Requesting permission")
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            !=PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            !=PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(this,android.Manifest.permission.RECORD_AUDIO)
            !=PackageManager.PERMISSION_GRANTED){
            println("Requesting permission")
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.RECORD_AUDIO),0
        }
    }

    private fun initUI(){
        val factory=InjectorUtils.provideRecorderViewModelFactory()
        viewModel=ViewModelProvider.of(this,factory).get(RecorderViewModel::class.java)
        addObserver()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun addObserver(){
        viewModel?.getRTime()?.observe(this, Observer {
            
        })
    }
}