package com.info.ionoviewgithuptask.starredprojects.repository

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.GithupRepositoryData
import com.info.ionoviewgithuptask.starredprojects.util.Resource

interface StarredProjectsDefaultRepository {
    suspend fun getStarredProjects(
        searchKeyWord: String,
        sortBy: String,
        orderBy: String,
        currentPage: String,
    ):Resource<GithupRepositoryData>
}