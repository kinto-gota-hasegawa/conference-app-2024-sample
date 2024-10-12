package com.example.conference_app_2024_sample

import androidx.compose.runtime.compositionLocalOf
import kotlin.reflect.KClass

val LocalRepositories = compositionLocalOf<Map<KClass<*>, Any>> {
    error("No LocalRepository provided")
}