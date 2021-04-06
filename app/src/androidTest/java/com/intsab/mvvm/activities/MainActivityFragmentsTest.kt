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
import kotlinx.coroutines.delay
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityFragmentsTest {
    val targetContext: Context = ApplicationProvider.getApplicationContext()
    var dataCount: Int = 0

    @Before
    fun before() {

    }

    suspend fun delaySeconds(seconds: Long) {
        delay(timeMillis = seconds)
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

//    @Test
//    fun isDataLoaded(){
//        var firstItem:CommentsModel?= null
//        activityRule.scenario.onActivity { activity ->
//            firstItem = activity.firstItem
//        }
//        onData(allOf(`is`(instanceOf(CommentsModel::class.java)),
//            hasEntry(equalTo(firstItem), `is`(""))))
//    }

}