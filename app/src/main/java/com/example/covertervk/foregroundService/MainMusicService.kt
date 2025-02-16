package com.example.covertervk.foregroundService

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

class MainMusicService(): Service() {
    private var musicService: MusicService? = null
    private var isBound = false
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicBinder
            musicService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

//    override fun onStart() {
//        super.onStart()
//        Intent(this, MusicService::class.java).also { intent ->
//            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        if (isBound) {
//            unbindService(serviceConnection)
//            isBound = false
//        }
//    }

    private fun playMusic(trackUrl: String) {
        if (isBound) {
            musicService?.playTrack(trackUrl)
        }
    }

    private fun pauseMusic() {
        if (isBound) {
            musicService?.pauseTrack()
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}