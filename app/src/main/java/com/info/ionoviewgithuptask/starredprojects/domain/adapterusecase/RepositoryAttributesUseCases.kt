package com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.Item

fun getStarsCount(gitHupProjectItem: Item) =
    "Stars  ${gitHupProjectItem.stargazers_count}"

fun getIssueCount(gitHupProjectItem: Item) =
    "Issues  ${gitHupProjectItem.open_issues_count}"


fun getOwnerAvatarUrl(gitHupProject: Item) = gitHupProject.owner.avatar_url
fun getOwnerName(gitHupProject: Item) = gitHupProject.owner.login
fun getSimpleDate(gitHupProject: Item) =
    GitHupDateTimeConverterUseCase()(gitHupProject.created_at)