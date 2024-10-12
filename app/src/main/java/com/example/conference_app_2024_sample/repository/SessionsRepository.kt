package com.example.conference_app_2024_sample.repository

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.conference_app_2024_sample.LocalRepositories
import com.example.conference_app_2024_sample.collectAsRetainedState
import com.example.conference_app_2024_sample.data.timetable.Timetable
import com.example.conference_app_2024_sample.data.timetable.dataTimetableFlow


interface SessionsRepository {
    @Composable
    fun timetable(): Timetable
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
}


@Composable
fun localSessionsRepository(): SessionsRepository {
    return LocalRepositories.current[SessionsRepository::class] as SessionsRepository
}