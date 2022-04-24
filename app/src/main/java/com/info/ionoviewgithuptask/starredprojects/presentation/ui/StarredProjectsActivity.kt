package com.info.ionoviewgithuptask.starredprojects.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.info.ionoviewgithuptask.R
import com.info.ionoviewgithuptask.databinding.ActivityStarredProjetsBinding
import com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase.RepositoryCreationDateUseCase
import com.info.ionoviewgithuptask.starredprojects.domain.userinputusecase.FilterListOfItemUseCase
import com.info.ionoviewgithuptask.starredprojects.domain.userinputusecase.IsValid
import com.info.ionoviewgithuptask.starredprojects.presentation.adapter.StarredProjectAdapter
import com.info.ionoviewgithuptask.starredprojects.util.ErrorType
import com.info.ionoviewgithuptask.starredprojects.util.Resource
import com.info.ionoviewgithuptask.starredprojects.util.extensions.showInternetConnectionDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.*


@AndroidEntryPoint
class StarredProjectsActivity : AppCompatActivity() {
    lateinit var activityBinding: ActivityStarredProjetsBinding
    private val TAG = "StarredActivityTag"
    var currentScrollingPage = 1
    val isValid = IsValid()
    var isUserFiltering = false

    lateinit var mostStarredProjectAdapter: StarredProjectAdapter
    val starredProjectsViewModel: StarredProjectsViewModel by lazy {
        ViewModelProvider(this).get(StarredProjectsViewModel::class.java)
    }

    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityStarredProjetsBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)




        setSupportActionBar(activityBinding.starredProjectsActivityMainToolBar)
        mostStarredProjectAdapter = StarredProjectAdapter(layoutInflater)
        linearLayoutManager = LinearLayoutManager(this)

        activityBinding.starredProjectsActivityRecyclerViewProjects.run {
            layoutManager =
                linearLayoutManager
            adapter =
                mostStarredProjectAdapter
        }
        getDate(getTheDateOfLastMonth(), currentScrollingPage)
        createRecyclerViewPagination()

        collectItemsFromViewModel()

    }


    private fun collectItemsFromViewModel() {

        lifecycleScope.launchWhenStarted {
            starredProjectsViewModel.starredProjectsStateFlow.collect { result ->
                when (result) {
                    is Resource.Success -> {
                        mostStarredProjectAdapter.appendList(result.data?.items!!)
                        changeProgressBarVisibility(false)
                        Log.d(
                            TAG,
                            "onChanged: success ${currentScrollingPage}" + result.data?.items!!
                        )
                        currentScrollingPage++
                        changeLoadingDataViewVisibility(false)
                        changeStarredProjectRecyclerViewVisibility(true)
                        showInternetConnectionDialog(
                            false,
                            activityBinding.starredProjectsActivityInternetLayout.root
                        )

                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "onChanged: loading")

                    }
                    is Resource.Error -> {
                        changeProgressBarVisibility(false)
                        changeLoadingDataViewVisibility(false)
                        if (result.errorType == ErrorType.NO_INTERNET) {
                            showInternetConnectionDialog(
                                true,
                                activityBinding.starredProjectsActivityInternetLayout.root
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getDate(date: String, currentPage: Int) {
        starredProjectsViewModel.getData(date, "stars", "desc", currentPage.toString())

    }


    var isRecyclerViewStateScrolling = false
    private fun createRecyclerViewPagination() {
        activityBinding.starredProjectsActivityRecyclerViewProjects.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isRecyclerViewStateScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentItem = linearLayoutManager.childCount
                val itemCount = linearLayoutManager.itemCount
                val findFirstVisibleItemPosition =
                    linearLayoutManager.findFirstVisibleItemPosition()
                if (isRecyclerViewStateScrolling && (currentItem + findFirstVisibleItemPosition) == itemCount) {
                    if (!isUserFiltering) {
                        isRecyclerViewStateScrolling = false
                        changeProgressBarVisibility(true)

                        getDate(getTheDateOfLastMonth(), currentScrollingPage)
                    }


                }
            }
        })

    }

    private fun getTheDateOfLastMonth(): String =
        RepositoryCreationDateUseCase()(Calendar.getInstance())

    fun changeProgressBarVisibility(isVisible: Boolean) {
        activityBinding.starredProjectsActivityProgressBar.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun changeLoadingDataViewVisibility(isVisible: Boolean) {
        activityBinding.starredProjectsActivityViewLoadingDate.loadingDateView.visibility =
            if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    fun changeStarredProjectRecyclerViewVisibility(isVisible: Boolean) {
        activityBinding.starredProjectsActivityRecyclerViewProjects.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //search view
        menuInflater.inflate(R.menu.menu_search_items, menu)

        val searchItem = menu!!.findItem(R.id.menuAction_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getResourceString(R.string.search_hint)
        searchView.setOnQueryTextListener(searchListener)
        searchView.isIconified = false
        return true

    }


    fun isValidQueryInput(text: String) = isValid(text)
    val searchListener = (object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(p0: String?)=false

        override fun onQueryTextChange(input: String?): Boolean {
            if (isValidQueryInput(input!!)) {
                isUserFiltering = true
                val filteredProjectsListList =
                    FilterListOfItemUseCase()(input, mostStarredProjectAdapter.getItemsList())
                mostStarredProjectAdapter.setData(filteredProjectsListList)
                scrollRecyclerView(0)


            } else {
                isUserFiltering = false
                mostStarredProjectAdapter.setData(mostStarredProjectAdapter.getItemsList())
                scrollRecyclerView(0)

            }
            return true
        }


    })

    private fun scrollRecyclerView(position: Int) {
        activityBinding.starredProjectsActivityRecyclerViewProjects.smoothScrollToPosition(position)

    }

    private fun getResourceString(resourceId: Int) = resources.getString(resourceId)


}

