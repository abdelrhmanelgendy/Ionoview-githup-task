package com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase

import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.Item
import com.info.ionoviewgithuptask.starredprojects.data.remote.datamodels.gitHupprojects.Owner
import com.info.ionoviewgithuptask.starredprojects.util.Constant
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RepositoryAttributesUseCasesKtTest {

    @Test
    fun `get stars count with correct item returns correct value string`() {
        val starCount = 54
        val starStatement ="Stars  "
        val item = Item(stargazers_count = starCount)

        val starsCountStatementResult = getStarsCount(item)
        assertEquals(starsCountStatementResult, "$starStatement$starCount")

    }
    @Test
    fun `get open issues count with correct item returns correct value string`() {
        val issuesCount = 1150
        val issuesStatement ="Issues  "
        val item = Item(open_issues_count = issuesCount)

        val issuesCountStatementResult = getIssueCount(item)
        assertEquals(issuesCountStatementResult, "$issuesStatement$issuesCount")

    }

    @Test
    fun `get owner image url with correct item returns correct url`() {
        val imgUrl = "https://images.unsplash.com/photo-1533738363-b7f9aef128ce?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8Y3V0ZSUyMGNhdHN8ZW58MHx8MHx8&w=1000&q=80"
        val item = Item(owner = Owner(avatar_url = imgUrl))

        val avatarUrl = getOwnerAvatarUrl(item)
        assertEquals(avatarUrl, imgUrl)

    }
    @Test
    fun `get owner name with correct item returns correct name`() {
        val name = "Tom Hanks"
        val item = Item(owner = Owner(login = name))

        val ownerName = getOwnerName(item)
        assertEquals(name, ownerName)

    }

    @Test
    fun `get simplified creation date with correct gitHup date pattern returns correct date`() {
        val gitHupDate = "2022-04-10T05:42:40Z"
        val gitHupDateInSimpleFormat = "10-04-2022"

        val item = Item(created_at = gitHupDate)
        val simpleDateResult = getSimpleDate(item)
        assertEquals(gitHupDateInSimpleFormat, simpleDateResult)

    }

    @Test
    fun `get simplified creation date with empty gitHup date pattern returns unknown date`() {
        val gitHupDate = ""
        val gitHupDateInSimpleFormat = Constant.UN_KNOWN_DATE

        val item = Item(created_at = gitHupDate)
        val simpleDateResult = getSimpleDate(item)
        assertEquals(gitHupDateInSimpleFormat, simpleDateResult)

    }


}