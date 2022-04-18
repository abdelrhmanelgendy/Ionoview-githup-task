package com.info.ionoviewgithuptask.starredprojects.repository

import android.util.Log
import com.info.ionoviewgithuptask.starredprojects.data.remote.webservice.GitHupApi
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.GithupRepositoryData
import com.info.ionoviewgithuptask.starredprojects.util.ErrorType
import com.info.ionoviewgithuptask.starredprojects.util.Resource
import javax.inject.Inject

class StarredProjectsMainRepository
@Inject constructor(var gitHupApi: GitHupApi) :
    StarredProjectsDefaultRepository {
    private val TAG = "StarredProjectsRepoTag"
    override suspend fun getStarredProjects(
        searchKeyWord: String,
        sortBy: String,
        orderBy: String,
        currentPage: String
    ): Resource<GithupRepositoryData> {
        try {
            val repositoryResponse = gitHupApi.getMostStarredRepositoriesByDate(
                searchKeyWord,
                sortBy,
                orderBy,
                currentPage
            )
            if (repositoryResponse.isSuccessful) {
                return Resource.Success(repositoryResponse.body())

            } else {
                val errorMsg=repositoryResponse.message()
                Log.d(TAG, "getStarredProjects: "+errorMsg)
                 return Resource.Error(ErrorType.UNKNOWN, errorMsg)
            }
        } catch (e: Exception) {
            return Resource.Error(ErrorType.UNKNOWN, e.message!!)

        }
    }

}