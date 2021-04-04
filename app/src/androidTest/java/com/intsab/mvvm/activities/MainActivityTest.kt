package com.intsab.mvvm.activities

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import com.intsab.mvvm.R
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.concurrent.thread


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    val targetContext: Context = ApplicationProvider.getApplicationContext()
    var dataCount: Int = 0

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun before() {
        activityRule.scenario.onActivity { activity ->
            Thread.sleep(7_000)  // wait to get Data
            dataCount = 5
        }
    }

    @Test
    fun test_is_text_correct() {
        onView(withId(R.id.tvNoData)).check(
            matches(
                ViewMatchers.withText(
                    targetContext.resources.getString(
                        R.string.no_data_available
                    )
                )
            )
        )
    }

    @Test
    fun test_no_data_visibility() {
        onView(withId(R.id.tvNoData)).check(
            matches(
                ViewMatchers.withText(
                    targetContext.resources.getString(
                        R.string.no_data_available
                    )
                )
            )
        )
        if (dataCount > 0)
            onView(withId(R.id.tvNoData)).check(matches(not(isDisplayed())))
        else
            onView(withId(R.id.tvNoData)).check(matches(isDisplayed()))

    }


}