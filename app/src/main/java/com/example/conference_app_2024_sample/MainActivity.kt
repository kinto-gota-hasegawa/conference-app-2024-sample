package com.example.conference_app_2024_sample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.conference_app_2024_sample.data.timetable.TimetableItemId
import com.example.conference_app_2024_sample.repository.DefaultSessionsRepository
import com.example.conference_app_2024_sample.repository.RepositoryProvider
import com.example.conference_app_2024_sample.repository.SessionsRepository
import com.example.conference_app_2024_sample.timetable.TIMETABLE_SCREEN_ROUTE
import com.example.conference_app_2024_sample.timetable.TimetableScreen
import com.example.conference_app_2024_sample.timetableItemDetail.TIMETABLE_ITEM_DETAIL_SCREEN_ROUTE
import com.example.conference_app_2024_sample.timetableItemDetail.TimetableItemDetailScreen
import com.example.conference_app_2024_sample.ui.theme.Conferenceapp2024sampleTheme
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import io.github.takahirom.rin.rememberRetained
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
object MainActivityModule {

    @Provides
    @IntoMap
    @IntKey(1)
    fun provideInt1(): String {
        return "ABC"
    }

    @Provides
    @IntoMap
    @IntKey(2)
    fun provideInt2(): String {
        return "DEF"
    }

}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var repositoryProvider: RepositoryProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Conferenceapp2024sampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    repositoryProvider.Provide {
                        KaigiNavHost(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun KaigiNavHost(
    modifier: Modifier = Modifier,
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TIMETABLE_SCREEN_ROUTE,
        modifier = modifier,
    ) {
        composable(
            route = TIMETABLE_SCREEN_ROUTE,
        ) {
            TimetableScreen(
                onTimetableItemClick = { id ->
                    navController.navigate(TIMETABLE_ITEM_DETAIL_SCREEN_ROUTE + "/${id.value}")
                },
                onTestClick = {
                    navController.navigate(TEST_SCREEN_ROUTE)
                },
            )
        }

        composable(
            route = "$TIMETABLE_ITEM_DETAIL_SCREEN_ROUTE/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("itemId")!!
            TimetableItemDetailScreen(
                id = TimetableItemId(id)
            )
        }

        composable(
            route = TEST_SCREEN_ROUTE
        ) {
            TestScreen()
        }
    }

}


const val TEST_SCREEN_ROUTE = "testScreenRoute"
@Composable
fun TestScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "TestScreen"
        )
    }
}