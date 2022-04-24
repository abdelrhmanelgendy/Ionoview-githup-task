package com.info.ionoviewgithuptask.starredprojects.domain.userinputusecase

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.Item
import java.util.*

class FilterListOfItemUseCase {
    val isInputValid = IsValid()
    operator fun invoke(input: String, updatedList: List<Item>): List<Item> {
        if (!isInputValid(input)) {
            return emptyList()
        }
        val userInput = input.lowercase(Locale.getDefault())

        return updatedList.filter { item ->
            filterByName(item, userInput) ||
                    filterByDescription(item, userInput) ||
                    filterByStars(item, userInput) ||
                    filterByIssues(item, userInput) ||
                   filterBuOwnerName(item,userInput)
        }


    }

    private fun filterBuOwnerName(item: Item, userInput: String)=
        item.owner?.login?.lowercase(Locale.getDefault())
            ?.contains(userInput)!!


    private fun filterByIssues(item: Item, userInput: String) =
        item.open_issues_count.toString()
            .lowercase(Locale.getDefault())
            .contains(userInput)


    private fun filterByStars(item: Item, userInput: String): Boolean =
        item.stargazers_count.toString().lowercase(Locale.getDefault())
            .contains(userInput)


    private fun filterByDescription(item: Item, userInput: String) =
        (item.description != null && item.description.lowercase(Locale.getDefault())
            .contains(userInput))


    private fun filterByName(item: Item, userInput: String) =
        item.name?.lowercase(Locale.getDefault())?.contains(userInput)!!

}