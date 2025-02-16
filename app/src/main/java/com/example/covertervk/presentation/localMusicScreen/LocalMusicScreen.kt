package com.example.covertervk.presentation.localMusicScreen

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.covertervk.presentation.apiMusicScreen.ApiScreenMusicViewModel


@Composable
fun LocalMusicScreen(viewModel: AudioViewModel = hiltViewModel()) {
    val audioFiles by viewModel.audioFiles.observeAsState(emptyList())

    LazyColumn {
        items(audioFiles) { audioFile ->
            AudioFileItem(audioFile) {
                viewModel.playAudio(audioFile.uri)
            }
        }
    }
}

@Composable
fun AudioFileItem(audioFile: AudioViewModel.AudioFile, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = audioFile.title)
            Text(text = audioFile.artist)
        }
        Text(
            text = formatDuration(audioFile.duration),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

fun formatDuration(duration: Long): String {
    val minutes = (duration / 1000) / 60
    val seconds = (duration / 1000) % 60
    return String.format("%02d:%02d", minutes, seconds)
}


