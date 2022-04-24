package com.info.ionoviewgithuptask.starredprojects.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.info.ionoviewgithuptask.R
import com.info.ionoviewgithuptask.databinding.GithupProjectElementBinding
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.Item
import com.info.ionoviewgithuptask.starredprojects.domain.LoadPhoto
import com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase.*

class StarredProjectAdapter constructor(private val layoutInflater: LayoutInflater) :

    RecyclerView.Adapter<StarredProjectAdapter.StarredProjectViewHolder>() {

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
        val projectsDiffUtil = StarredProjectsDiffUtil(oldProjectItemsList, updatedList)
        val calculateDiffResult = DiffUtil.calculateDiff(projectsDiffUtil)
        oldProjectItemsList = updatedList
        calculateDiffResult.dispatchUpdatesTo(this)
    }

    private lateinit var distinctByIdList: List<Item>

    //used to migrate two lists
    fun appendList(list: List<Item>) {
        val currentList =
            oldProjectItemsList.toMutableList()
        currentList.addAll(list)
        distinctByIdList = DistinctListOfProjects()(currentList)
        setData(distinctByIdList)
    }

    fun getItemsList(): List<Item> {
        if (::distinctByIdList.isInitialized && !distinctByIdList.isEmpty())
            return distinctByIdList;
        else
            return emptyList()
    }

    inner class StarredProjectViewHolder(
        val holderBinding: GithupProjectElementBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(holderBinding.root) {

        fun bindDateToViews(position: Int) {
            val gitHupProjectItem = oldProjectItemsList.get(position)

            getOwnerAvatarUrl(gitHupProjectItem)?.let {
                LoadPhoto().invoke(
                    holderBinding.gitHupProjectElementImgOwnerAvatar,
                    it,
                    R.mipmap.ic_launcher,
                    context
                )
            }

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
            holderBinding.gitHupProjectElementTVRepositoryCreationDate.setText(
                getSimpleDate(
                    gitHupProjectItem
                )
            )

        }


    }


}