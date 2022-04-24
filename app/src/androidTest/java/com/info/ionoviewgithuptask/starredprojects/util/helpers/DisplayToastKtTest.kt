package com.info.ionoviewgithuptask.starredprojects.util.helpers

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.info.ionoviewgithuptask.R
import com.info.ionoviewgithuptask.starredprojects.presentation.starredproject.StarredProjectsActivity
import com.info.ionoviewgithuptask.starredprojects.util.Constant.NO_CONNECTION
import com.info.ionoviewgithuptask.starredprojects.util.ToastMatcher
import org.junit.Rule
import org.junit.Test

class DisplayToastKtTest {

    @get:Rule
    public var activityScenarioRule: ActivityScenarioRule<StarredProjectsActivity> =
        ActivityScenarioRule(StarredProjectsActivity::class.java)

    @Test
    fun testPostingAToastByTextIsWorking() {

        val launch = ActivityScenario.launch(StarredProjectsActivity::class.java)
        launch.onActivity {

            displayToast(NO_CONNECTION, it.applicationContext)
        }
        onView(withText(NO_CONNECTION)).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))

    }

    @Test
    fun testPostingAToastByResourceIsWorking() {

        val launch = ActivityScenario.launch(StarredProjectsActivity::class.java)
        launch.onActivity {

            displayToast(R.string.dummyText, it.applicationContext)
        }
        onView(withText(R.string.dummyText)).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))

    }



}