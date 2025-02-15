package com.example.covertervk.foregroundService

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.core.app.NotificationCompat
import com.example.covertervk.R

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var notificationManager: NotificationManager
    private val binder = MusicBinder()

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
    }

    fun playTrack(trackUrl: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(trackUrl)
        mediaPlayer.prepare()
        mediaPlayer.start()
        showNotification()
    }

    fun pauseTrack() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            showNotification()
        }
    }

    fun resumeTrack() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            showNotification()
        }
    }

    private fun showNotification() {
        val playPauseAction = if (mediaPlayer.isPlaying) {
            NotificationCompat.Action(
                R.drawable.baseline_pause_24, "Pause",
                getPendingIntent(ACTION_PAUSE)
            )
        } else {
            NotificationCompat.Action(
                R.drawable.baseline_play_arrow_24, "Play",
                getPendingIntent(ACTION_PLAY)
            )
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Playing Music")
            .setContentText("Track Name")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(NotificationCompat.Action(
                R.drawable.baseline_play_arrow_24, "Previous",
                getPendingIntent(ACTION_PREVIOUS)
            ))
            .addAction(playPauseAction)
            .addAction(NotificationCompat.Action(
                R.drawable.baseline_play_arrow_24, "Next",
                getPendingIntent(ACTION_NEXT)
            ))
            .build()

            //startForeground(NOTIFICATION_ID, notification)
    }

    private fun getPendingIntent(action: String): PendingIntent {
        val intent = Intent(this, MusicReceiver::class.java).apply {
            this.action = action
        }
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Music Playback",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    companion object {
        const val CHANNEL_ID = "music_playback_channel"
        const val NOTIFICATION_ID = 1
        const val ACTION_PLAY = "action_play"
        const val ACTION_PAUSE = "action_pause"
        const val ACTION_NEXT = "action_next"
        const val ACTION_PREVIOUS = "action_previous"
    }
}
