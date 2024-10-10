package com.example.conference_app_2024_sample.data.timetable

import kotlinx.coroutines.flow.flow

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

enum class TimetableUiType {
    List,
    Grid,
}

val dataTimetableFlow = flow {
    emit(DATA_TIMETABLE)
}

val DATA_TIMETABLE = Timetable(
    items = List(10) {
        TimetableItem(
            id = TimetableItemId("id$it"),
            title = "Session $it",
        )
    },
    bookmarks = setOf(),
)
