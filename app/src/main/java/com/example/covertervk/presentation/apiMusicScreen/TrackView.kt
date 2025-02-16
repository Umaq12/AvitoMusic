package com.example.covertervk.presentation.apiMusicScreen

import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.example.covertervk.R
import com.example.covertervk.domain.model.TrackDomain

@Composable
fun TrackItem(
    track: TrackDomain,
    isPlaying: Boolean,
    onPlayClick:  () -> Unit,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = track.album.coverUrl
                ?: "https://cdn-images.dzcdn.net/images/artist/bb76c2ee3b068726ab4c37b0aabdb57a/1000x1000-000000-80-0-0.jpg",
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = track.title, fontWeight = FontWeight.Bold, color = Color.Red)
            track.artist.name?.let { Text(text = it, color = Color.Gray) }
        }

        IconButton(
            onClick = onItemClick
        ) {
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
    }
}
