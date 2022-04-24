package com.info.ionoviewgithuptask.starredprojects.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.GitHupRepositoryData
import com.info.ionoviewgithuptask.starredprojects.domain.repository.StarredProjectsMainRepository
import com.info.ionoviewgithuptask.starredprojects.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StarredProjectsViewModel
@Inject constructor(
    private val starredProjectsMainRepository:
    StarredProjectsMainRepository
) :
    ViewModel() {



    private val _starredProjectsMutableLiveData: MutableLiveData<Resource<GitHupRepositoryData>> =
        MutableLiveData()
    val starredProjectsLiveData: LiveData<Resource<GitHupRepositoryData>> =
        _starredProjectsMutableLiveData

    fun getData(
        searchKeyWord: String,
        sortBy: String,
        orderBy: String,
        currentPage: String
    ) {
        _starredProjectsMutableLiveData.value = Resource.Loading()
        viewModelScope.launch {
            val starredProjects = starredProjectsMainRepository.getStarredProjects(
                searchKeyWord,
                sortBy,
                orderBy,
                currentPage
            )
            _starredProjectsMutableLiveData.value = starredProjects
        }
    }

}