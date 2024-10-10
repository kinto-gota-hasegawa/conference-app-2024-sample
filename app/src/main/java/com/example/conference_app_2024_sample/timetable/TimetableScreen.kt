package com.example.conference_app_2024_sample.timetable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.conference_app_2024_sample.EventFlow
import com.example.conference_app_2024_sample.data.timetable.TimetableItem
import com.example.conference_app_2024_sample.data.timetable.TimetableUiType
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
        onTimetableUiChangeClick = {
            eventFlow.tryEmit(TimetableScreenEvent.UiTypeChange)
        },
        modifier = modifier,
    )
}

data class TimetableScreenUiState(
    val contentUiState: TimetableUiState,
    val timetableUiType: TimetableUiType,
)

sealed interface TimetableUiState {
    data object Empty : TimetableUiState
    data class ListTimetable(
        val items: List<TimetableItem>,
    ): TimetableUiState
    data class GridTimetable(
        val items: List<TimetableItem>,
    ): TimetableUiState
}

@Composable
fun TimetableScreen(
    uiState: TimetableScreenUiState,
    onTimetableUiChangeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Text(
                text = "Timetable",
                modifier = Modifier.weight(1f),
            )
            when (uiState.timetableUiType) {
                TimetableUiType.List -> {
                    Button(
                        onClick = onTimetableUiChangeClick,
                    ) {
                        Text(
                            text = "To Grid",
                            modifier = Modifier.padding(end = 16.dp),
                        )
                    }
                }
                TimetableUiType.Grid -> {
                    Button(
                        onClick = onTimetableUiChangeClick,
                    ) {
                        Text(
                            text = "To List",
                            modifier = Modifier.padding(end = 16.dp),
                        )
                    }
                }
            }
        }
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
                LazyColumn {
                    items(contentUiState.items) { item ->
                        Text(
                            text = item.title,
                            modifier = Modifier.padding(top = 12.dp, start = 16.dp)
                        )
                    }
                }
            }
            is TimetableUiState.GridTimetable -> {
                LazyVerticalGrid(GridCells.Fixed(2)) {
                    items(contentUiState.items) { item ->
                        Text(
                            text = item.title,
                            modifier = Modifier.padding(top = 12.dp, start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}