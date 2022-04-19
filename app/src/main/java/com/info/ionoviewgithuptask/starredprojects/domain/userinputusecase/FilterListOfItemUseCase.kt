package com.info.ionoviewgithuptask.starredprojects.domain.userinputusecase

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.Item
import java.util.*

class FilterListOfItemUseCase {
    val isInputValid = IsValid()
    operator fun invoke(input: String, updatedList: List<Item>): List<Item> {
        if (!isInputValid(input)) {
            return emptyList()
        }
        val userInput = input.lowercase(Locale.getDefault())
        val temporaryProjectsList = updatedList
        val filteredProjectsList = temporaryProjectsList.filter { item ->
            item.name.lowercase(Locale.getDefault()).contains(userInput) ||
                    (item.description != null && item.description.lowercase(Locale.getDefault())
                        .contains(userInput)) ||
                    item.stargazers_count.toString().lowercase(Locale.getDefault())
                        .contains(userInput) || item.open_issues_count.toString()
                .lowercase(Locale.getDefault())
                .contains(userInput) || item.owner.login.lowercase(Locale.getDefault())
                .contains(userInput)
        }
        return filteredProjectsList


    }
}