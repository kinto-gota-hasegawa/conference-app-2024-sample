package com.example.conference_app_2024_sample.timetable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.conference_app_2024_sample.EventFlow
import com.example.conference_app_2024_sample.data.timetable.TimetableItem
import com.example.conference_app_2024_sample.data.timetable.TimetableItemId
import com.example.conference_app_2024_sample.data.timetable.TimetableUiType
import com.example.conference_app_2024_sample.rememberEventFlow

const val TIMETABLE_SCREEN_ROUTE = "timetableScreenRoute"

@Composable
fun TimetableScreen(
    onTimetableItemClick: (id: TimetableItemId) -> Unit,
    onTestClick: () -> Unit,
    modifier: Modifier = Modifier,
    eventFlow: EventFlow<TimetableScreenEvent> = rememberEventFlow(),
    uiState: TimetableScreenUiState = timetableScreenPresenter(
        events = eventFlow
    ),
) {
    TimetableScreen(
        uiState = uiState,
        onTimetableItemClick = onTimetableItemClick,
        onTestClick = onTestClick,
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
    onTimetableItemClick: (id: TimetableItemId) -> Unit,
    onTestClick: () -> Unit,
    onTimetableUiChangeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Timetable",
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = onTestClick) {
                Icon(Icons.Default.Search, "")
            }
            Spacer(modifier = Modifier.width(4.dp))
            when (uiState.timetableUiType) {
                TimetableUiType.List -> {
                    IconButton(onClick = onTimetableUiChangeClick) {
                        Icon(Icons.Default.Menu, "")
                    }
                }
                TimetableUiType.Grid -> {
                    IconButton(onClick = onTimetableUiChangeClick) {
                        Icon(Icons.AutoMirrored.Default.List, "")
                    }
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
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
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(contentUiState.items) { item ->
                        ListItem(
                            item = item,
                            onClickItem = onTimetableItemClick,
                        )
                    }
                }
            }
            is TimetableUiState.GridTimetable -> {
                LazyVerticalGrid(
                    GridCells.Fixed(3),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(contentUiState.items) { item ->
                        ListItem(
                            item = item,
                            onClickItem = onTimetableItemClick
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun ListItem(
    item: TimetableItem,
    onClickItem: (id: TimetableItemId) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onClickItem(item.id) }
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 12.dp),
    ) {
        Text(
            text = item.title,
        )
    }
}