package com.example.conference_app_2024_sample.timetableItemDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.example.conference_app_2024_sample.EventFlow
import com.example.conference_app_2024_sample.data.timetable.TimetableItemId
import com.example.conference_app_2024_sample.rememberEventFlow
import com.example.conference_app_2024_sample.repository.SessionsRepository
import com.example.conference_app_2024_sample.repository.localSessionsRepository

sealed interface TimetableItemDetailEvent {
}

@Composable
fun timetableItemDetailScreen(
    timetableItemId: TimetableItemId,
    eventFlow: EventFlow<TimetableItemDetailEvent> = rememberEventFlow(),
    sessionsRepository: SessionsRepository = localSessionsRepository(),
): TimetableItemDetailScreenUiState {
    val timetableItemWithBookMark by rememberUpdatedState(
        sessionsRepository.timetableItemWithBookmark(timetableItemId)
    )

    val timetableItemStateWithBookmarkValue = timetableItemWithBookMark
        ?: return TimetableItemDetailScreenUiState.Loading(timetableItemId)

    return TimetableItemDetailScreenUiState.Loaded(
        timetableItem = timetableItemStateWithBookmarkValue.first,
        isBookmarked = timetableItemStateWithBookmarkValue.second
    )
}