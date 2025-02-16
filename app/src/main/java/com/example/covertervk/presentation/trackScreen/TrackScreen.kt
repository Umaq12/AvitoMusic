package com.example.covertervk.presentation.trackScreen

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.covertervk.R
import com.example.covertervk.domain.model.TrackDomain
import com.example.covertervk.navigation.Screen
import com.example.covertervk.presentation.apiMusicScreen.ApiScreenMusicViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackScreen(
    trackId: Long,
    navController: NavController,
    viewModel: ApiScreenMusicViewModel = hiltViewModel()
) {
    Log.d("66", trackId.toString())
    val state by viewModel.state
    val track = state.chart?.tracks?.find { it.id == trackId }
    LaunchedEffect(trackId) {
        viewModel.getTrackById(trackId)
    }
    val isPlaying by viewModel.isPlaying.collectAsState()
    val currentPosition = viewModel.currentPosition.collectAsState().value.toFloat()
    val duration = viewModel.state.value.track?.duration



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = track?.title ?: "Track Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        track?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = track.album.coverUrl,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))


                Text(text = track.title)
                Spacer(modifier = Modifier.height(8.dp))
                track.artist.name?.let { Text(text = it) }

                Spacer(modifier = Modifier.height(16.dp))

                Slider(
                    value = currentPosition,
                    onValueChange = { viewModel.seekTo(it.toInt()) },
                    valueRange = 0f..(duration?.toFloat() ?: 1f),
                    modifier = Modifier.fillMaxWidth()
                )

                if (duration != null) {
                    Text(
                        text = "${formatTime(currentPosition.toLong())} / ${formatTime(duration.toLong())}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))

                // Кнопки управления воспроизведением
                Row {
                    IconButton(onClick = { viewModel.previousTrack(navController) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Previous")
                    }

                    IconButton(onClick = {
                        if (isPlaying) {
                            viewModel.pause()
                        } else {
                            track.previewUrl?.let { viewModel.play(it) }
                        }
                    }) {
                        val iconResId = if (isPlaying) {
                            R.drawable.baseline_pause_24
                        } else {
                            R.drawable.baseline_play_arrow_24
                        }
                        Icon(
                            painter = painterResource(id = iconResId),
                            contentDescription = if (isPlaying) "Pause" else "Play"
                        )
                    }

                    IconButton(onClick = { viewModel.nextTrack() }) {
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next")
                    }
                }
            }
        }
    }
}

// Форматирование времени
private fun formatTime(seconds: Long): String {
    val minutes = (seconds / 60).toInt()
    val remainingSeconds = (seconds % 60).toInt()
    return String.format("%02d:%02d", minutes, remainingSeconds)
}



