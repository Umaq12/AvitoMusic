package com.example.covertervk.presentation.trackScreen

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.covertervk.R
import com.example.covertervk.presentation.apiMusicScreen.ApiScreenMusicViewModel


@Composable
fun TrackScreen(
    viewModel: ApiScreenMusicViewModel,
    trackId: String,
    navController: NavController
) {
    val track = viewModel.getTrackById(trackId)
    val mediaPlayer = remember { MediaPlayer() }
    var isPlaying by remember { mutableStateOf(false) }

    // Подготовка и воспроизведение трека при загрузке экрана
//    LaunchedEffect(trackId) {
//        track?.previewUrl?.let { url ->
//            mediaPlayer.setDataSource(url)
//            mediaPlayer.prepare()
//            mediaPlayer.start()
//            isPlaying = true
//        }
//    }

    // Освобождение ресурсов MediaPlayer при выходе с экрана
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Text(
//            text = track ?: "Unknown Title",
//            fontWeight = FontWeight.Bold,
//            fontSize = 24.sp,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//        Text(
//            text = track?.artist?.name ?: "Unknown Artist",
//            fontSize = 18.sp,
//            color = Color.Gray,
//            modifier = Modifier.padding(bottom= 16.dp)
//        )
        IconButton(
            onClick = {
                isPlaying = !isPlaying
                if (isPlaying) {
                    mediaPlayer.start()
                } else {
                    mediaPlayer.pause()
                }
            },
            modifier = Modifier.size(64.dp)
        ) {
            val iconResId = if (isPlaying) {
                R.drawable.baseline_pause_24
            } else {
                R.drawable.baseline_play_arrow_24
            }
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = if (isPlaying) "Pause" else "Play",
                modifier = Modifier.size(64.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Назад")
        }
    }
}

