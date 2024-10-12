package com.example.conference_app_2024_sample.timetableItemDetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conference_app_2024_sample.data.timetable.TimetableItem
import com.example.conference_app_2024_sample.data.timetable.TimetableItemId
import com.example.conference_app_2024_sample.data.timetable.TimetableUiType

const val TIMETABLE_ITEM_DETAIL_SCREEN_ROUTE = "timetableItemDetailScreenRoute"

@Composable
fun TimetableItemDetailScreen(
    id: TimetableItemId,
    modifier: Modifier = Modifier,
    uiState: TimetableItemDetailScreenUiState = timetableItemDetailScreen(
        timetableItemId = id,
    ),
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Timetable Item Detail",
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { }) {
                Icon(Icons.Default.FavoriteBorder, "")
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (uiState is TimetableItemDetailScreenUiState.Loaded) {
            Text(
                text = uiState.timetableItem.title,
                fontSize = 24.sp,
            )
        }
        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = uiState is TimetableItemDetailScreenUiState.Loading,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                )
            }
        }
    }
}

sealed interface TimetableItemDetailScreenUiState {
    val timetableItemId: TimetableItemId
    data class Loading(
        override val timetableItemId: TimetableItemId
    ): TimetableItemDetailScreenUiState
    data class Loaded(
        val timetableItem: TimetableItem,
        val isBookmarked: Boolean,
    ): TimetableItemDetailScreenUiState {
        override val timetableItemId: TimetableItemId
            get() = timetableItem.id
    }
}