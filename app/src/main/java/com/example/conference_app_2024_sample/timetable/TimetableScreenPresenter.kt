package com.example.conference_app_2024_sample.timetable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import com.example.conference_app_2024_sample.EventEffect
import com.example.conference_app_2024_sample.EventFlow
import com.example.conference_app_2024_sample.data.timetable.TimetableUiType
import com.example.conference_app_2024_sample.repository.DefaultSessionsRepository
import com.example.conference_app_2024_sample.repository.SessionsRepository
import io.github.takahirom.rin.rememberRetained

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
    var timetableUiType by rememberRetained { mutableStateOf(TimetableUiType.List) }

    val timetableUiState by rememberUpdatedState(
        if (sessions.items.isEmpty()) {
            TimetableUiState.Empty
        } else {
            when (timetableUiType) {
                TimetableUiType.List -> TimetableUiState.ListTimetable(sessions.items)
                TimetableUiType.Grid -> TimetableUiState.GridTimetable(sessions.items)
            }
        }
    )

    EventEffect(events) { event ->
        when (event) {
            is TimetableScreenEvent.Bookmark -> {}
            TimetableScreenEvent.UiTypeChange -> {
                timetableUiType = when (timetableUiType) {
                    TimetableUiType.List -> TimetableUiType.Grid
                    TimetableUiType.Grid -> TimetableUiType.List
                }
            }
        }
    }

    return TimetableScreenUiState(
        contentUiState = timetableUiState,
        timetableUiType = timetableUiType,
    )
}