package com.example.covertervk.foregroundService

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

class MusicReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val serviceIntent = Intent(context, MusicService::class.java)
        when (intent.action) {
            MusicService.ACTION_PLAY -> serviceIntent.action = MusicService.ACTION_PLAY
            MusicService.ACTION_PAUSE -> serviceIntent.action = MusicService.ACTION_PAUSE
            MusicService.ACTION_NEXT -> serviceIntent.action = MusicService.ACTION_NEXT
            MusicService.ACTION_PREVIOUS -> serviceIntent.action = MusicService.ACTION_PREVIOUS
        }
        ContextCompat.startForegroundService(context, serviceIntent)
    }
}
