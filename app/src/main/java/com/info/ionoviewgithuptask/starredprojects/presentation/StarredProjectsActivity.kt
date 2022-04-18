package com.info.ionoviewgithuptask.starredprojects.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.ionoviewgithuptask.databinding.ActivityStarredProjetsBinding
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.GithupRepositoryData
import com.info.ionoviewgithuptask.starredprojects.presentation.adapter.MostStarredProjectAdapter
import com.info.ionoviewgithuptask.starredprojects.util.Resource
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class StarredProjectsActivity : AppCompatActivity() {
    lateinit var activityBinding: ActivityStarredProjetsBinding
    private val TAG = "StarredActivityTag"
    var currentScrollingPage = 1
    lateinit var mostStarredProjectAdapter: MostStarredProjectAdapter
    val starredProjectsViewModel: StarredProjectsViewModel by lazy {
        ViewModelProvider(this).get(StarredProjectsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityStarredProjetsBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        val starredProjectsViewModel: StarredProjectsViewModel =
            ViewModelProvider(this).get(StarredProjectsViewModel::class.java)

        mostStarredProjectAdapter = MostStarredProjectAdapter(layoutInflater)
        activityBinding.starredProjectsActivityRecyclerViewProjects.layoutManager =
            LinearLayoutManager(this)
        activityBinding.starredProjectsActivityRecyclerViewProjects.adapter =
            mostStarredProjectAdapter
        starredProjectsViewModel.getData("created:>2017-10-22", "stars", "desc", "1")

        starredProjectsViewModel.starredProjectsLiveData.observe(this,
            object : Observer<Resource<GithupRepositoryData>> {
                override fun onChanged(result: Resource<GithupRepositoryData>?) {
                    when (result) {
                        is Resource.Success -> {
                            mostStarredProjectAdapter.setData(result.data?.items!!)
                            Log.d(TAG, "onChanged: success " + result.data)

                        }
                        is Resource.Loading -> {
                            Log.d(TAG, "onChanged: loading")

                        }
                        is Resource.Error -> {
                            Log.d(TAG, "onChanged: " + result.message)
                        }

                    }

                }
            })

    }
}

