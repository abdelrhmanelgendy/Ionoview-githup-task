package com.info.ionoviewgithuptask.starredprojects.data.remote.webservice

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.GithupRepositoryData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GitHupApi {

    @GET("repositories")
    suspend fun getMostStarredRepositoriesByDate(
        @Query("q") searchKeyWord: String,
        @Query("sort") sortBy: String,
        @Query("ord") orderBy: String,
        @Query("page") currentPage: String ):Response<GithupRepositoryData>
}