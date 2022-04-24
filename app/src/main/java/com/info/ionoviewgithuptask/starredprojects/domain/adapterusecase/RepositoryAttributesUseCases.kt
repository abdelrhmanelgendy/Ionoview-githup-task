package com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.Item
import com.info.ionoviewgithuptask.starredprojects.util.Constant

fun getStarsCount(gitHupProjectItem: Item): String {

    return "Stars  ${gitHupProjectItem.stargazers_count}"
}

fun getIssueCount(gitHupProjectItem: Item) =
    "Issues  ${gitHupProjectItem.open_issues_count}"


fun getOwnerAvatarUrl(gitHupProject: Item) = gitHupProject.owner?.avatar_url

fun getOwnerName(gitHupProject: Item) = gitHupProject.owner?.login


fun getSimpleDate(gitHupProject: Item): String {
    if (gitHupProject.created_at!!.isEmpty()) {
        return Constant.UN_KNOWN_DATE
    }
    return GitHupDateTimeConverterUseCase()(date = gitHupProject.created_at)
}
