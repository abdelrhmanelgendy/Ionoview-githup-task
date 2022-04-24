package com.info.ionoviewgithuptask.starredprojects.domain.repository

import com.info.ionoviewgithuptask.starredprojects.data.remote.webservice.GitHupApi
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.GitHupRepositoryData
import com.info.ionoviewgithuptask.starredprojects.util.helpers.NetworkStatusHelper
import com.info.ionoviewgithuptask.starredprojects.util.ErrorType
import com.info.ionoviewgithuptask.starredprojects.util.Resource
import javax.inject.Inject

class StarredProjectsMainRepository
@Inject constructor(var gitHupApi: GitHupApi, var networkStatusHelper: NetworkStatusHelper) :
    StarredProjectsDefaultRepository {
    override suspend fun getStarredProjects(
        searchKeyWord: String,
        sortBy: String,
        orderBy: String,
        currentPage: String
    ): Resource<GitHupRepositoryData> {
        try {
            if (!networkStatusHelper.isNetworkConnected()) {
                return Resource.Error(ErrorType.NO_INTERNET)
            }
            val repositoryResponse = gitHupApi.getMostStarredRepositoriesByDate(
                searchKeyWord,
                sortBy,
                orderBy,
                currentPage
            )
            if (repositoryResponse.isSuccessful) {
                return Resource.Success(repositoryResponse.body())

            } else {
                val errorMsg = repositoryResponse.message()
                 return Resource.Error(ErrorType.UNKNOWN, errorMsg)
            }
        } catch (e: Exception) {
            return Resource.Error(ErrorType.UNKNOWN, e.message!!)

        }
    }

}