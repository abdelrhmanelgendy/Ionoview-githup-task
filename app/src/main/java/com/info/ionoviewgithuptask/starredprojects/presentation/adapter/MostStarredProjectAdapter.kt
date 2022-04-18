package com.info.ionoviewgithuptask.starredprojects.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.info.ionoviewgithuptask.databinding.GithupProjectElementBinding
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.Item
import com.info.ionoviewgithuptask.starredprojects.util.convertGitHupDateTimeToSimpleDate

class MostStarredProjectAdapter constructor(private val layoutInflater: LayoutInflater) :

    RecyclerView.Adapter<MostStarredProjectAdapter.StarredProjectViewHolder>() {

    lateinit var gitHupItemBinding: GithupProjectElementBinding
    private var oldProjectItemsList = emptyList<Item>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredProjectViewHolder {
        gitHupItemBinding = GithupProjectElementBinding.inflate(layoutInflater)
        return StarredProjectViewHolder(gitHupItemBinding, parent.context!!)
    }

    override fun onBindViewHolder(holder: StarredProjectViewHolder, position: Int) {
        holder.bindDateToViews(position)
    }

    override fun getItemCount(): Int {
        return oldProjectItemsList.size
    }

    fun setData(updatedList: List<Item>) {
        Log.d("TAG212", "setData: " + updatedList)
        val projectsDiffUtil = StarredProjectsDiffUtil(oldProjectItemsList, updatedList)
        val calculateDiffResult = DiffUtil.calculateDiff(projectsDiffUtil)
        oldProjectItemsList = updatedList

        calculateDiffResult.dispatchUpdatesTo(this)
    }

    fun appendList(list: List<Item>) {
        val currentList =
            oldProjectItemsList.toMutableList() // get the current adapter list as a mutated list
        currentList.addAll(list)
        val distinctBy = currentList.distinctBy { it.id }
        setData(distinctBy)
    }

    inner class StarredProjectViewHolder(
        val holderBinding: GithupProjectElementBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(holderBinding.root) {

        fun bindDateToViews(position: Int) {
            val gitHupProjectItem = oldProjectItemsList.get(position)

            Glide.with(context).load(getOwnerAvatarUrl(gitHupProjectItem))
                .into(holderBinding.gitHupProjectElementImgOwnerAvatar)

            holderBinding.gitHupProjectElementTVRepositoryName.setText(gitHupProjectItem.name)

            holderBinding.gitHupProjectElementTVRepositoryOwnerName.setText(
                getOwnerName(
                    gitHupProjectItem
                )
            )

            holderBinding.gitHupProjectElementBtnProjectStarsCount.setText(
                getStarsCount(
                    gitHupProjectItem
                )
            )
            holderBinding.gitHupProjectElementBtnProjectIssueCount.setText(
                getIssueCount(
                    gitHupProjectItem
                )
            )
            holderBinding.gitHupProjectElementTVRepositoryDescription.setText(gitHupProjectItem.description)
            holderBinding.gitHupProjectElementTVRepositoryCreationDate.setText(gitHupProjectItem.id.toString())

        }

        private fun getStarsCount(gitHupProjectItem: Item) =
            "Stars ${gitHupProjectItem.stargazers_count}"

        private fun getIssueCount(gitHupProjectItem: Item) =
            "Issues ${gitHupProjectItem.open_issues_count}"

    }

    private fun getOwnerAvatarUrl(gitHupProject: Item) = gitHupProject.owner.avatar_url
    private fun getOwnerName(gitHupProject: Item) = gitHupProject.owner.login
    private fun getSimpleDate(gitHupProject: Item) =
        convertGitHupDateTimeToSimpleDate(gitHupProject.created_at)
//                                                                                             2018-02-22T22:23:19Z
}