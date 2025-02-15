package com.example.covertervk.presentation.apiMusicScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.covertervk.navigation.Screen
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiMusicScreen(viewModel: ApiScreenMusicViewModel, navController: NavController) {
    val state = viewModel.state.value
    var searchQuery by remember { mutableStateOf("") }
    var debounceSearchQuery by remember { mutableStateOf("") }
    var playingTrackId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(searchQuery) {
        delay(300)
        debounceSearchQuery = searchQuery
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.error.isNotEmpty() -> {
                Text(text = "Ошибка", color = Color.Red)
            }
            state.chart != null -> {
                val tracks = state.chart.tracks
                val filteredTracks = tracks.filter {
                    it.title.contains(debounceSearchQuery, ignoreCase = true) ||
                            it.artist.name?.contains(debounceSearchQuery, ignoreCase = true) == true
                }

                Column {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Поиск") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(Icons.Default.Clear, contentDescription = "Очистить")
                                }
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(filteredTracks) { track ->
                            TrackItem(
                                track = track,
                                isPlaying = track.id.toString() == playingTrackId,
                                onPlayClick = {
                                    playingTrackId = track.id.toString()
                                    navController.navigate(Screen.TrackScreen.createRoute(track.id.toString()))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


