package com.intsab.mvvm.fragments

import androidx.paging.PagedListAdapter
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.intsab.mvvm.R
import com.intsab.mvvm.activities.MainActivity
import kotlinx.coroutines.*
import org.hamcrest.CoreMatchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityFragmentTest {
    var dataCount: Int = 0
    lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun before() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity {

           val count= GlobalScope.async {
                delaySeconds(5000)
               (it.supportFragmentManager.fragments.first() as MainActivityFragment).getMyDataCount()
            }
            GlobalScope.launch {
                dataCount= count.await()
            }


        }
    }

    @Test
    fun is_no_data_shown_correctly() {


    }

    @Test
    fun is_loader_hidden_correctly() {

    }

    @Test
    fun items_loaded_correctly() {

    }

    @Test
    fun details_loaded_correctly() {
        onData(allOf(`is`(instanceOf(PagedListAdapter::class.java)), `is`("alias odio sit")))
            .perform(click())
        onView(withId(R.id.tv_name)).check(matches(withText(containsString("Eclipse"))))
    }

    suspend fun delaySeconds(seconds: Long) {
        delay(seconds)
    }
}