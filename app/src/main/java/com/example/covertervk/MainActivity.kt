package com.example.covertervk

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.covertervk.navigation.BottomNavigationBar
import com.example.covertervk.navigation.Screen
import com.example.covertervk.presentation.apiMusicScreen.ApiMusicScreen
import com.example.covertervk.presentation.apiMusicScreen.ApiScreenMusicViewModel
import com.example.covertervk.presentation.localMusicScreen.AudioViewModel
import com.example.covertervk.presentation.localMusicScreen.LocalMusicScreen
import com.example.covertervk.presentation.theme.ui.Avito
import com.example.covertervk.presentation.trackScreen.TrackScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ApiScreenMusicViewModel by viewModels()
    private val audioViewModel: AudioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestStoragePermission()

        setContent {
            Avito {
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
                            LocalMusicScreen()
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

    // В вашем Activity или Fragment
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Разрешение предоставлено, загружаем аудиофайлы
            audioViewModel.loadAudioFiles()
        } else {
            // Разрешение отклонено, отображаем сообщение пользователю
            Toast.makeText(this, "Разрешение на чтение хранилища отклонено", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestStoragePermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Разрешение уже предоставлено
                audioViewModel.loadAudioFiles()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                // Показать объяснение необходимости разрешения
                showPermissionRationale()
            }
            else -> {
                // Запросить разрешение
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun showPermissionRationale() {
        AlertDialog.Builder(this)
            .setTitle("Требуется разрешение")
            .setMessage("Для отображения аудиофайлов необходимо предоставить доступ к хранилищу.")
            .setPositiveButton("Предоставить") { _, _ ->
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            .setNegativeButton("Отмена", null)
            .create()
            .show()
    }


}



