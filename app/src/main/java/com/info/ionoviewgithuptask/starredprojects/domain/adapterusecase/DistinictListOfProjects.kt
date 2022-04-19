package com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.Item

class DistinctListOfProjects{
    operator fun invoke(items:List<Item>)=items.toMutableList().distinctBy{i->i.id}
}