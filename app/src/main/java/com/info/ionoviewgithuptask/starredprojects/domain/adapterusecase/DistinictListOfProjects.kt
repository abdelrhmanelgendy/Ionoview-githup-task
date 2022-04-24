package com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.Item

class DistinctListOfProjects {
    operator fun invoke(items: List<Item>): List<Item> {
        if (items.isEmpty())
        {
            return emptyList()
        }
        return items.toMutableList().distinctBy { i -> i.id }
    }
}