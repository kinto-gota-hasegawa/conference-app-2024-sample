package com.example.conference_app_2024_sample.timetableItemDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.example.conference_app_2024_sample.EventEffect
import com.example.conference_app_2024_sample.EventFlow
import com.example.conference_app_2024_sample.data.timetable.TimetableItemId
import com.example.conference_app_2024_sample.repository.SessionsRepository
import com.example.conference_app_2024_sample.repository.localSessionsRepository

sealed interface TimetableItemDetailEvent {
    data class Bookmark(
        val id: TimetableItemId,
    ): TimetableItemDetailEvent
}

@Composable
fun timetableItemDetailScreen(
    timetableItemId: TimetableItemId,
    events: EventFlow<TimetableItemDetailEvent>,
    sessionsRepository: SessionsRepository = localSessionsRepository(),
): TimetableItemDetailScreenUiState {
    val timetableItemWithBookMark by rememberUpdatedState(
        sessionsRepository.timetableItemWithBookmark(timetableItemId)
    )

    EventEffect(events) { event ->
        when (event) {
            is TimetableItemDetailEvent.Bookmark -> {
                sessionsRepository.toggleBookmark(event.id)
            }
        }
    }

    val timetableItemStateWithBookmarkValue = timetableItemWithBookMark
        ?: return TimetableItemDetailScreenUiState.Loading(timetableItemId)

    return TimetableItemDetailScreenUiState.Loaded(
        timetableItem = timetableItemStateWithBookmarkValue.first,
        isBookmarked = timetableItemStateWithBookmarkValue.second
    )
}