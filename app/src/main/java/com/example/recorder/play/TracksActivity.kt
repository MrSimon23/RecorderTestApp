package com.example.recorder.play


import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recorder.R
import com.example.recorder.item.Track
import com.example.recorder.record.RecorderViewModel
import com.example.recorder.util.InjectorUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.activity_recordings.*
import kotlinx.android.synthetic.main.activity_recordings.view.*


class TracksActivity:AppCompatActivity(){
    private var viewModel:TrackViewModel?=null
    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private var data:ArrayList<String>?=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordings)
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initUi()
    }

    override fun onStart() {
        super.onStart()

        recordings_recycleview.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=groupAdapter
        }
    }

    private fun initUi(){
        val factory=InjectorUtils.provideTrackViewModelFactory()
        viewModel=ViewModelProviders.of(this,factory).get(TrackViewModel::class.java)
        updateAdapter()
    }

    private fun updateAdapter(){
        data=viewModel?.getTracks()
        println("Updating adapter")
        groupAdapter.clear()

        if (data!=null){
            data!!.forEach{
                println("Data: $it")
                groupAdapter.add(Track(it,this))
            }
        }
    }
}