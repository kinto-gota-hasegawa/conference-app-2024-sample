package com.example.conference_app_2024_sample.data.timetable

data class Timetable(
    val items: List<TimetableItem> = listOf(),
    val bookmarks: Set<TimetableItemId> = setOf(),
)

data class TimetableItem(
    val id: TimetableItemId,
    val title: String,
)

data class TimetableItemId(
    val value: String,
)
