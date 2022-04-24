package com.info.ionoviewgithuptask.starredprojects.presentation


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.info.ionoviewgithuptask.R
import com.info.ionoviewgithuptask.starredprojects.presentation.ui.StarredProjectsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class StarredProjectsActivityTest {


    @Test
    fun loading_data_view_is_shown_when_the_app_starting() {
        val launch = ActivityScenario.launch(StarredProjectsActivity::class.java)
        onView(withId(R.id.starredProjectsActivity_viewLoadingDate))
            .check(matches(isDisplayed()))
    }

    @Test
    fun search_view_in_view_and_clickable() {
        ActivityScenario.launch(StarredProjectsActivity::class.java)

        onView(withId(R.id.menuAction_search))
            .perform(click())


        onView(withId(R.id.search_src_text)).perform(typeText("Re"));

    }

    @Test
    fun test_filtering_starred_project_recycler_view_wit_real_data() {
        ActivityScenario.launch(StarredProjectsActivity::class.java)

        onView(withId(R.id.starredProjectsActivity_viewLoadingDate))
            .check(matches(isDisplayed()))


        onView(withId(R.id.menuAction_search))
            .perform(click())


        onView(withId(R.id.search_src_text)).perform(typeText("Re"));
        onView(withText("Re")).check(matches(isDisplayed()))


    }

}