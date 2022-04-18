package com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels

data class GithupRepositoryData(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)