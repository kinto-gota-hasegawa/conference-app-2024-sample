package com.example.conference_app_2024_sample.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.conference_app_2024_sample.RepositoryProvider
import com.example.conference_app_2024_sample.timetable.TIMETABLE_SCREEN_EMPTY_TAG
import com.example.conference_app_2024_sample.timetable.TIMETABLE_SCREEN_LIST_TAG
import com.example.conference_app_2024_sample.timetable.TimetableScreen
import com.example.conference_app_2024_sample.ui.theme.Conferenceapp2024sampleTheme

class TimetableScreenRobot(
    private val composeTestRule: ComposeContentTestRule,
) {

    fun setupTimetableScreenContent() {
        composeTestRule.setContent {
            Conferenceapp2024sampleTheme {
                RepositoryProvider {
                    TimetableScreen(
                        onTimetableItemClick = {},
                        onTestClick = {},
                    )
                }
            }
        }
    }

    fun waitUntilIdle() {
        composeTestRule.waitForIdle()
    }

    fun checkListVisible() {
        composeTestRule
            .onNodeWithTag(TIMETABLE_SCREEN_LIST_TAG)
            .assertIsDisplayed()
    }

    fun checkEmptyVisible() {
        composeTestRule
            .onAllNodes(hasTestTag(TIMETABLE_SCREEN_EMPTY_TAG))
            .onFirst()
            .assertIsDisplayed()
    }


    fun clickFirstSession() {
        composeTestRule
            .onAllNodes(hasTestTag("TimetableItemCard"))
            .onFirst()
            .performClick()
        composeTestRule.waitForIdle()
    }

}