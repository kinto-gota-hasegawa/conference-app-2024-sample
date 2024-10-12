package com.example.conference_app_2024_sample.repository

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.example.conference_app_2024_sample.LocalRepositories
import com.example.conference_app_2024_sample.collectAsRetainedState
import com.example.conference_app_2024_sample.data.timetable.Timetable
import com.example.conference_app_2024_sample.data.timetable.TimetableItem
import com.example.conference_app_2024_sample.data.timetable.TimetableItemId
import com.example.conference_app_2024_sample.data.timetable.dataTimetableFlow


interface SessionsRepository {
    @Composable
    fun timetable(): Timetable

    @Composable
    fun timetableItemWithBookmark(id: TimetableItemId): Pair<TimetableItem, Boolean>?
}

class DefaultSessionsRepository(

): SessionsRepository {
    @Composable
    override fun timetable(): Timetable {
        val timetable by remember {
            dataTimetableFlow
        }.collectAsRetainedState(Timetable())

        return timetable
    }

    @Composable
    override fun timetableItemWithBookmark(id: TimetableItemId): Pair<TimetableItem, Boolean>? {
        val timetable by rememberUpdatedState(timetable())
        val itemWithBookmark = remember(id, timetable) {
            val timetableItem = timetable.items.firstOrNull { it.id == id } ?: return@remember null
            timetableItem to false
        }
        return itemWithBookmark
    }
}


@Composable
fun localSessionsRepository(): SessionsRepository {
    return LocalRepositories.current[SessionsRepository::class] as SessionsRepository
}