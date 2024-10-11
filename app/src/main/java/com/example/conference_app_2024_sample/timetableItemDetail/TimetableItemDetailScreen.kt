package com.example.conference_app_2024_sample.timetableItemDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

const val TIMETABLE_ITEM_DETAIL_SCREEN_ROUTE = "timetableItemDetailScreenRoute"

@Composable
fun TimetableItemDetailScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "TimetableItemDetailScreen"
        )
    }
}