package com.example.conference_app_2024_sample.repository

import androidx.compose.runtime.Composable
import com.example.conference_app_2024_sample.data.timetable.Timetable
import com.example.conference_app_2024_sample.data.timetable.TimetableItem
import com.example.conference_app_2024_sample.data.timetable.TimetableItemId


interface SessionsRepository {
    @Composable
    fun timetable(): Timetable

    @Composable
    fun timetableItemWithBookmark(id: TimetableItemId): Pair<TimetableItem, Boolean>
}

class DefaultSessionsRepository(

): SessionsRepository {
    @Composable
    override fun timetable(): Timetable {
        return Timetable(items = listOf(TimetableItem(TimetableItemId("abc"), title = "AIUEO")), bookmarks = setOf())
    }

    @Composable
    override fun timetableItemWithBookmark(id: TimetableItemId): Pair<TimetableItem, Boolean> {
        TODO("Not yet implemented")
    }

}

