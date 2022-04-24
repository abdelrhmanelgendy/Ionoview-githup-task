package com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.Item
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DistinctListOfProjectsTest {

    @Test
    fun `distinct list with the same items remove duplication and return unique item ids`() {
        val listOfTheSameItems =
            listOf(buildRepositoryItem(), buildRepositoryItem(), buildRepositoryItem())
        val distinctListOfProjects = DistinctListOfProjects().invoke(listOfTheSameItems)
        assertEquals(distinctListOfProjects.size, 1)
    }

    @Test
    fun `distinct different list items return the same list`() {
        val listOfTheSameItems =
            listOf(buildRepositoryItem(10), buildRepositoryItem(20), buildRepositoryItem(30))
        val distinctListOfProjects = DistinctListOfProjects().invoke(listOfTheSameItems)
        assertEquals(distinctListOfProjects.size, 3)
    }

    @Test
    fun `distinct list with no items return empty list`() {
        val listOfTheSameItems:List<Item> =
            listOf()

        val distinctListOfProjects = DistinctListOfProjects().invoke(listOfTheSameItems)
        val emptyItemList:List<Item> = emptyList()
        assertEquals(distinctListOfProjects, emptyItemList)
    }

    private fun buildRepositoryItem() =
        Item(id = 50)

    private fun buildRepositoryItem(id:Int) =
        Item(id = id)

}