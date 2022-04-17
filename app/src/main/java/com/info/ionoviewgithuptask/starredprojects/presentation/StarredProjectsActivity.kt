package com.info.ionoviewgithuptask.starredprojects.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.info.ionoviewgithuptask.databinding.ActivityStarredProjetsBinding

class StarredProjectsActivity : AppCompatActivity() {
    lateinit var activityBinding: ActivityStarredProjetsBinding

    var currentScrollingPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityStarredProjetsBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
    }
}