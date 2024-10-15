package com.example.conference_app_2024_sample.repository

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.conference_app_2024_sample.LocalRepositories
import javax.inject.Inject

class RepositoryProvider @Inject constructor(
    repositories: Map<Class<out Any>, @JvmSuppressWildcards Any>,
) {
    private val repositoriesMap = repositories
        .map { (k, v) ->
            k.kotlin to v as Any
        }.toMap()

    @Composable
    fun Provide(content: @Composable () -> Unit) {
        CompositionLocalProvider(
            LocalRepositories provides repositoriesMap,
        ) {
            content()
        }
    }
}