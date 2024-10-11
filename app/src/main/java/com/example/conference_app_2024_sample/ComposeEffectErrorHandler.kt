package com.example.conference_app_2024_sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import io.github.takahirom.rin.produceRetainedState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun <T: R, R> Flow<T>.collectAsRetainedState(
    initial: R,
    context: CoroutineContext = EmptyCoroutineContext,
): State<R> {
    return produceRetainedState(initial, this, context) {
        if (context == EmptyCoroutineContext) {
            collect { value = it }
        } else withContext(context) {
            collect { value = it }
        }
    }
}