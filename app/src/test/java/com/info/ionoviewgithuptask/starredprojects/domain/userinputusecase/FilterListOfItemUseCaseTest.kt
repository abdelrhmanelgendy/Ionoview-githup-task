package com.info.ionoviewgithuptask.starredprojects.domain.userinputusecase

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.Item
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.Owner
import com.info.ionoviewgithuptask.starredprojects.util.helpers.NetworkStatusHelper

import org.junit.Assert.*
import org.junit.Test
import org.junit.matchers.JUnitMatchers.containsString
import org.junit.matchers.JUnitMatchers.hasItem
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`


@RunWith(JUnit4::class)
class FilterListOfItemUseCaseTest {


    @Test
    fun `filter list of items with empty input returns empty list`() {
        val filterListOfItemUseCase = FilterListOfItemUseCase()("", buildListOfItems())
        assertEquals(0, filterListOfItemUseCase.size)
    }

    @Test
    fun `filter list of items with correct repo name input returns correct item`() {
        val filterListOfItemUseCase = FilterListOfItemUseCase()(buildFirstItem().name!!, buildListOfItems())
        assertThat(filterListOfItemUseCase,hasItem(buildFirstItem()))
    }

    @Test
    fun `filter list of items with part of the repo description input returns correct item`() {
        val filterListOfItemUseCase = FilterListOfItemUseCase()("simple way",buildListOfItems())
        assertThat(filterListOfItemUseCase.get(0).description, containsString("simple"))
    }


    @Test
    fun `filter list of items with the repo starts count as input returns correct item`() {
        val filterListOfItemUseCase = FilterListOfItemUseCase()("101",buildListOfItems())
        assertEquals(filterListOfItemUseCase.get(0).stargazers_count.toString(), "101")
    }

    @Test
    fun `filter list of items with the repo issues count as input returns correct item`() {
        val filterListOfItemUseCase = FilterListOfItemUseCase()("623",buildListOfItems())
        assertEquals(filterListOfItemUseCase.get(0).open_issues_count.toString(), "623")
    }

    @Test
    fun `filter list of items with the repo owner name as input returns correct item`() {
        val filterListOfItemUseCase = FilterListOfItemUseCase()("xotahal",buildListOfItems())
        assertEquals(filterListOfItemUseCase.get(0).owner?.login.toString(), "xotahal")
    }


    private fun buildFirstItem() = Item(
        name = "react-native-motion",
        description = "Animate it! Easily!",
        stargazers_count = 101,
        open_issues_count = 51,
        owner = Owner(login = "xotahal")
    )
    private fun buildSecondItem() = Item(
        name = "workly",
        description = "A really simple way to move a function or class to a web worker",
        stargazers_count = 2051,
        open_issues_count = 623,
        owner = Owner(login = "pshihn"))

    private fun buildListOfItems(): List<Item> {

        return listOf(buildFirstItem(), buildSecondItem())
    }
}