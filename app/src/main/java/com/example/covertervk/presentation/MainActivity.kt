package com.example.covertervk.presentation

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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.covertervk.foregroundService.MusicService
import com.example.covertervk.navigation.BottomNavigationBar
import com.example.covertervk.navigation.Screen
import com.example.covertervk.presentation.apiMusicScreen.ApiMusicScreen
import com.example.covertervk.presentation.apiMusicScreen.ApiScreenMusicViewModel

import com.example.covertervk.presentation.theme.ui.CoverterVkTheme
import com.example.covertervk.presentation.trackScreen.TrackScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ApiScreenMusicViewModel by viewModels()
    private var musicService: MusicService? = null
    private var isBound = false
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicBinder
            musicService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MusicService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }

    private fun playMusic(trackUrl: String) {
        if (isBound) {
            musicService?.playTrack(trackUrl)
        }
    }

    private fun pauseMusic() {
        if (isBound) {
            musicService?.pauseTrack()
        }
    }

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
                            ExchangeScreen(viewModel)
                        }
                        composable(Screen.ApiMusicScreen.route) {
                            ApiMusicScreen(viewModel, navController)
                        }
                        composable(
                            route = Screen.TrackScreen.route,
                            arguments = listOf(navArgument("trackId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val trackId = backStackEntry.arguments?.getString("trackId")
                            trackId?.let {
                                TrackScreen(viewModel, trackId, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}


