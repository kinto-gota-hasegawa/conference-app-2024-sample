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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


interface SessionsRepository {
    @Composable
    fun timetable(): Timetable

    @Composable
    fun timetableItemWithBookmark(id: TimetableItemId): Pair<TimetableItem, Boolean>?

    fun toggleBookmark(id: TimetableItemId)
}

class DefaultSessionsRepository(

): SessionsRepository {

    private val bookmarkedIds = MutableStateFlow(setOf<TimetableItemId>())

    @Composable
    override fun timetable(): Timetable {
        val timetable by remember {
            dataTimetableFlow
        }.collectAsRetainedState(Timetable())

        val bookmarkedIds by remember {
            bookmarkedIds
        }.collectAsRetainedState(setOf())

        return timetable.copy(bookmarks = bookmarkedIds)
    }

    @Composable
    override fun timetableItemWithBookmark(id: TimetableItemId): Pair<TimetableItem, Boolean>? {
        val timetable by rememberUpdatedState(timetable())
        val itemWithBookmark = remember(id, timetable) {
            val timetableItem = timetable.items.firstOrNull { it.id == id } ?: return@remember null
            timetableItem to timetable.bookmarks.contains(id)
        }
        return itemWithBookmark
    }

    override fun toggleBookmark(id: TimetableItemId) {
        bookmarkedIds.update {
            it.toMutableSet().apply {
                if (contains(id)) {
                    remove(id)
                } else {
                    add(id)
                }
            }
        }
    }
}


@Composable
fun localSessionsRepository(): SessionsRepository {
    return LocalRepositories.current[SessionsRepository::class] as SessionsRepository
}