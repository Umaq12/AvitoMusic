package com.example.covertervk.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.covertervk.domain.model.TrackDomain

@Composable
fun TrackItem(track: TrackDomain, onClick: ()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
//        Image(
//            painter = rememberImagePainter(
//                data = track. ?: "url_заглушки"
//            ),
//            contentDescription = null,
//            modifier = Modifier.size(64.dp)
//        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = track.title, fontWeight = FontWeight.Bold, color = Color.Red)
            Text(text = track.artist.name, color = Color.Gray)
        }
    }
}