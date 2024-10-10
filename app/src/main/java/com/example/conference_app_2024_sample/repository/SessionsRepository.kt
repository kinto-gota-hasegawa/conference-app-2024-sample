package com.example.conference_app_2024_sample.repository

import androidx.compose.runtime.Composable
import com.example.conference_app_2024_sample.data.timetable.DATA_TIMETABLE
import com.example.conference_app_2024_sample.data.timetable.Timetable


interface SessionsRepository {
    @Composable
    fun timetable(): Timetable
}

class DefaultSessionsRepository(

): SessionsRepository {
    @Composable
    override fun timetable(): Timetable {
        return DATA_TIMETABLE
    }
}

