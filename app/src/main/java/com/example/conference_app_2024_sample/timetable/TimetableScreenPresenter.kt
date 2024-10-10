package com.example.conference_app_2024_sample.timetable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.example.conference_app_2024_sample.EventFlow
import com.example.conference_app_2024_sample.repository.DefaultSessionsRepository
import com.example.conference_app_2024_sample.repository.SessionsRepository

sealed interface TimetableScreenEvent {
    data class Bookmark(
        val id: Int,
        val bookmarked: Boolean,
    ): TimetableScreenEvent

    data object UiTypeChange: TimetableScreenEvent
}

@Composable
fun timetableScreenPresenter(
    events: EventFlow<TimetableScreenEvent>,
    sessionsRepository: SessionsRepository = remember { DefaultSessionsRepository() }
): TimetableScreenUiState {
    val sessions by rememberUpdatedState(sessionsRepository.timetable())

    val timetableUiState by rememberUpdatedState(
        if (sessions.items.isEmpty()) {
            TimetableUiState.Empty
        } else {
            TimetableUiState.ListTimetable(sessions.items)
        }
    )

    return TimetableScreenUiState(
        contentUiState = timetableUiState,
    )
}