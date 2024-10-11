package com.example.conference_app_2024_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.conference_app_2024_sample.timetable.TIMETABLE_SCREEN_ROUTE
import com.example.conference_app_2024_sample.timetable.TimetableScreen
import com.example.conference_app_2024_sample.timetableItemDetail.TIMETABLE_ITEM_DETAIL_SCREEN_ROUTE
import com.example.conference_app_2024_sample.timetableItemDetail.TimetableItemDetailScreen
import com.example.conference_app_2024_sample.ui.theme.Conferenceapp2024sampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Conferenceapp2024sampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KaigiNavHost(modifier = Modifier.padding(innerPadding))
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
                onTimetableItemClick = {
                    navController.navigate(TIMETABLE_ITEM_DETAIL_SCREEN_ROUTE)
                }
            )
        }

        composable(
            route = TIMETABLE_ITEM_DETAIL_SCREEN_ROUTE,
        ) {
            TimetableItemDetailScreen()
        }
    }

}