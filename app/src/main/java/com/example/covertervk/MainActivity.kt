package com.example.covertervk

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.covertervk.foregroundService.MusicService
import com.example.covertervk.navigation.BottomNavigationBar
import com.example.covertervk.navigation.Screen
import com.example.covertervk.presentation.ExchangeScreen
import com.example.covertervk.presentation.apiMusicScreen.ApiMusicScreen
import com.example.covertervk.presentation.apiMusicScreen.ApiScreenMusicViewModel

import com.example.covertervk.presentation.theme.ui.CoverterVkTheme
import com.example.covertervk.presentation.trackScreen.TrackScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ApiScreenMusicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoverterVkTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ApiMusicScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.ExchangeScreen.route) {
                            ExchangeScreen()
                        }
                        composable(Screen.ApiMusicScreen.route) {
                            ApiMusicScreen(
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        composable(
                            route = Screen.TrackScreen.route,
                            arguments = listOf(navArgument("trackId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val trackId = backStackEntry.arguments?.getLong("trackId") ?: 0L
                            TrackScreen(trackId = trackId, navController = navController)
                        }
                    }
                }
            }
        }
    }
}


