package com.info.ionoviewgithuptask.starredprojects.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityStarredProjetsBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

//        activityBinding.starredProjectsActivityRecyclerViewProjects.setNestedScrollingEnabled(false);
        ViewCompat.setNestedScrollingEnabled(
            activityBinding.starredProjectsActivityRecyclerViewProjects,
            false
        )


        mostStarredProjectAdapter = MostStarredProjectAdapter(layoutInflater)
        linearLayoutManager = LinearLayoutManager(this)
        activityBinding.starredProjectsActivityRecyclerViewProjects.layoutManager =
            linearLayoutManager
        activityBinding.starredProjectsActivityRecyclerViewProjects.adapter =
            mostStarredProjectAdapter
        getDate("created:>2017-10-22", currentScrollingPage)

        createRecyclerViewPagination()

        addObserverOnViewModelLiveData()

    }


    private fun addObserverOnViewModelLiveData() {

        starredProjectsViewModel.starredProjectsLiveData.observe(this,
            object : Observer<Resource<GithupRepositoryData>> {
                override fun onChanged(result: Resource<GithupRepositoryData>?) {
                    when (result) {
                        is Resource.Success -> {
                            mostStarredProjectAdapter.appendList(result.data?.items!!)
                            changeProgressBarVisibility(false)
                            Log.d(TAG, "onChanged: success ${currentScrollingPage}" + result.data)
                            currentScrollingPage++


                        }
                        is Resource.Loading -> {
                            Log.d(TAG, "onChanged: loading")

                        }
                        is Resource.Error -> {
                            Log.d(TAG, "onChanged: $currentScrollingPage" + result.message)
                            changeProgressBarVisibility(false)

                        }

                    }

                }
            })
    }

    private fun getDate(date: String, currentPage: Int) {
        starredProjectsViewModel.getData(date, "stars", "desc", currentPage.toString())

    }

    var isScrolling = false
    private fun createRecyclerViewPagination() {
//        activityBinding.starredProjectsActivityNastedScrollView.getViewTreeObserver().addOnScrollChangedListener(OnScrollChangedListener {
//            val view = activityBinding.starredProjectsActivityNastedScrollView.getChildAt(activityBinding.starredProjectsActivityNastedScrollView.getChildCount() - 1) as View
//            val diff: Int = view.bottom - (activityBinding.starredProjectsActivityNastedScrollView.getHeight() + activityBinding.starredProjectsActivityNastedScrollView
//                .getScrollY())
//            Log.d(TAG, "createRecyclerViewPagination: "+diff)
//            if (diff == 0) {
//
//                currentScrollingPage++
//                getDate("created:>2017-10-22", currentScrollingPage)
//            }
//        })

        activityBinding.starredProjectsActivityRecyclerViewProjects.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentItem = linearLayoutManager.childCount
                val itemCount = linearLayoutManager.itemCount
                val findFirstVisibleItemPosition =
                    linearLayoutManager.findFirstVisibleItemPosition()
                if (isScrolling && (currentItem + findFirstVisibleItemPosition) == itemCount) {
                    isScrolling = false
                    changeProgressBarVisibility(true)

                    getDate("created:>2017-10-22", currentScrollingPage)
                }
            }
        })

    }

    fun changeProgressBarVisibility(isVisible:Boolean)
    {
        activityBinding.starredProjectsActivityProgressBar.visibility= if (isVisible){View.VISIBLE} else {View.GONE}
    }
}

