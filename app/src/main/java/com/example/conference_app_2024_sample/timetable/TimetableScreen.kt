package com.example.conference_app_2024_sample.timetable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.conference_app_2024_sample.EventFlow
import com.example.conference_app_2024_sample.data.timetable.TimetableItem
import com.example.conference_app_2024_sample.rememberEventFlow

@Composable
fun TimetableScreen(
    modifier: Modifier = Modifier,
    eventFlow: EventFlow<TimetableScreenEvent> = rememberEventFlow(),
    uiState: TimetableScreenUiState = timetableScreenPresenter(
        events = eventFlow
    ),
) {
    TimetableScreen(
        uiState = uiState,
        modifier = modifier,
    )
}

data class TimetableScreenUiState(
    val contentUiState: TimetableUiState,
)

sealed interface TimetableUiState {
    data object Empty : TimetableUiState
    data class ListTimetable(
        val items: List<TimetableItem>,
    ): TimetableUiState
}


@Composable
fun TimetableScreen(
    uiState: TimetableScreenUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (val contentUiState = uiState.contentUiState) {
            is TimetableUiState.Empty -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("Empty")
                }
            }
            is TimetableUiState.ListTimetable -> {
                contentUiState.items.forEach {
                    Text(
                        text = it.title,
                        modifier = Modifier.padding(top = 12.dp, start = 16.dp)
                    )
                }
            }
        }
    }
}