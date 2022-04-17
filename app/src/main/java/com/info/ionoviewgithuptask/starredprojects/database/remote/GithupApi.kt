package com.info.ionoviewgithuptask.starredprojects.database.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface GithupApi {

    @GET("repositories")
    suspend fun getMostStarredRepositoriesByDate(
        @Query("q") creationDate: String,
        @Query("sort") sortBy: String,
        @Query("ord") orderBy: String,
        @Query("page") currentPage: String,

        )
}