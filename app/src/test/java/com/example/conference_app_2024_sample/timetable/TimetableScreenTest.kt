package com.example.conference_app_2024_sample.timetable

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.conference_app_2024_sample.robot.TimetableScreenRobot
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TimetableScreenTest {


    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testTimetableScreen() {
        val robot = TimetableScreenRobot(composeTestRule)
        with(robot) {
            setupTimetableScreenContent()
            waitUntilIdle()
            //checkEmptyVisible()
            checkListVisible()
        }
    }

}
