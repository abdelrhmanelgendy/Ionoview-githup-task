package com.info.ionoviewgithuptask.starredprojects.repository

import com.info.ionoviewgithuptask.starredprojects.data.remote.webservice.GitHupApi
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.GithupRepositoryData
import com.info.ionoviewgithuptask.starredprojects.util.ErrorType
import com.info.ionoviewgithuptask.starredprojects.util.Resource
import javax.inject.Inject

class StarredProjectsMainRepository
@Inject constructor(var gitHupApi: GitHupApi) :
    StarredProjectsDefaultRepository {

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
//                repositoryResponse.code()
                return Resource.Error(ErrorType.UNKNOWN, repositoryResponse.message())
            }
        } catch (e: Exception) {
            return Resource.Error(ErrorType.UNKNOWN, e.message!!)

        }
    }

}