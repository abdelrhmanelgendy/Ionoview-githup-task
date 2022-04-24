package com.info.ionoviewgithuptask.starredprojects.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.GitHupRepositoryData
import com.info.ionoviewgithuptask.starredprojects.domain.repository.StarredProjectsMainRepository
import com.info.ionoviewgithuptask.starredprojects.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StarredProjectsViewModel
@Inject constructor(
    private val starredProjectsMainRepository:
    StarredProjectsMainRepository
) :
    ViewModel() {


    private val _starredProjectsStateFlow: MutableStateFlow<Resource<GitHupRepositoryData>> =
        MutableStateFlow(Resource.Idle())
    val starredProjectsStateFlow: StateFlow<Resource<GitHupRepositoryData>> =
        _starredProjectsStateFlow

    fun getData(
        searchKeyWord: String,
        sortBy: String,
        orderBy: String,
        currentPage: String
    ) {
        viewModelScope.launch {
            _starredProjectsStateFlow.emit(Resource.Loading())
            val starredProjects = starredProjectsMainRepository.getStarredProjects(
                searchKeyWord,
                sortBy,
                orderBy,
                currentPage
            )
            _starredProjectsStateFlow.emit(starredProjects)
        }
    }

}