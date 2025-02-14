package com.example.covertervk.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun ExchangeScreen2(viewModel: ExchangeViewModel) {
    val state = viewModel.state.value
    Log.d("132", state.toString())
    var id by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.chart != null -> {

                val tracks = state.chart.tracks
                var searchQuery by remember { mutableStateOf("") }
                var debounceSearchQuery by remember { mutableStateOf("") }

                LaunchedEffect(searchQuery) {
                    delay(300)
                    debounceSearchQuery = searchQuery
                }

//                val filteredTracks by remember(debounceSearchQuery, tracks) {
//                    mutableStateOf(tracks.filter {
//                        it.title.contains(debounceSearchQuery, ignoreCase = true) ||
//                                it.artist.name.contains(debounceSearchQuery, ignoreCase = true)
//                    })
//                }

                Column {
                    Text("qeqwe", fontSize = 22.sp)
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Поиск") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(Icons.Default.Clear, contentDescription = "Очистить")
                                }
                            }
                        }
                    )
                    LazyColumn {
                        items(state.chart.tracks) { track ->
                            TrackItem(track, {})
                        }
                    }
                }
            }

            state.error.isNotEmpty() -> {
                viewModel.state.value.chart?.let { Text(text = "Ошибка", color = Color.Red) }
            }
        }
    }
}
