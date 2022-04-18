package com.info.ionoviewgithuptask.starredprojects.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.GithupRepositoryData
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.Item

typealias GitHupRepositories = List<Item>

class StarredProjectsDiffUtil constructor(
    private val oldList: GitHupRepositories,
    private val updatedList: GitHupRepositories
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = updatedList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList.get(oldItemPosition).id) == (updatedList.get(newItemPosition).id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList.get(oldItemPosition).id != updatedList.get(newItemPosition).id -> false
            oldList.get(oldItemPosition).node_id != updatedList.get(newItemPosition).node_id -> false
            oldList.get(oldItemPosition).name != updatedList.get(newItemPosition).name -> false
            else -> true
        }
    }
}