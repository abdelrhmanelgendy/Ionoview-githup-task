package com.info.ionoviewgithuptask.starredprojects.domain.repository

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.GitHupRepositoryData
import com.info.ionoviewgithuptask.starredprojects.util.Resource

interface StarredProjectsDefaultRepository {
    suspend fun getStarredProjects(
        searchKeyWord: String,
        sortBy: String,
        orderBy: String,
        currentPage: String,
    ):Resource<GitHupRepositoryData>
}