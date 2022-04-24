package com.info.ionoviewgithuptask.starredprojects.presentation.adapter

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.Item
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StarredProjectsDiffUtilTest {

    @Test
    fun `test getting the old and new list sizes with empty lists returns zero`() {
        val starredProjectsDiffUtil = StarredProjectsDiffUtil(oldList = buildEmptyOldList(),buildEmptyNewList())
        assertEquals(starredProjectsDiffUtil.newListSize,0)
        assertEquals(starredProjectsDiffUtil.oldListSize,0)
    }
    @Test
    fun `test getting the old and new list sizes with non empty lists returns correct size`() {
        val starredProjectsDiffUtil = StarredProjectsDiffUtil(oldList = buildOldList(),buildNewList())
        assertEquals(starredProjectsDiffUtil.newListSize,buildNewList().size)
        assertEquals(starredProjectsDiffUtil.oldListSize,buildOldList().size)
    }
    @Test
    fun `test getting the value of the content the same with the same first item returns true`() {
        val starredProjectsDiffUtil = StarredProjectsDiffUtil(oldList = buildOldList(),buildNewList())
        assertEquals(starredProjectsDiffUtil.areContentsTheSame(0,0),true)
    }

    @Test
    fun `test getting the value of the item the same with the same first item returns true`() {
        val starredProjectsDiffUtil = StarredProjectsDiffUtil(oldList = buildOldList(),buildNewList())
        assertEquals(starredProjectsDiffUtil.areItemsTheSame(0,0),true)
    }

    @Test
    fun `test getting the value of the content the same with the different second item returns false`() {
        val starredProjectsDiffUtil = StarredProjectsDiffUtil(oldList = buildOldList(),buildNewList())
        assertEquals(starredProjectsDiffUtil.areContentsTheSame(1,1),false)
    }



    private fun buildEmptyOldList(): List<Item> {
        return emptyList()
    }
    private fun buildOldList(): List<Item> {
        val item1= Item(id=100,node_id = "node 1",name = "first item")
        val item2= Item(id=101,node_id = "node 2",name = "second item")
        return listOf(item1,item2)
    }

    private fun buildEmptyNewList(): List<Item> {
        return emptyList()
    }
    private fun buildNewList(): List<Item> {
        val item1= Item(id=100,node_id = "node 1",name = "first item")
        val item2= Item(id=201,node_id = "node 4",name = "second item")
        return listOf(item1,item2)
    }

}