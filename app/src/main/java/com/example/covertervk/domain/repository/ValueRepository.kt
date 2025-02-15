package com.example.covertervk.domain.repository

import com.example.covertervk.data.remote.dto.MusicChart
import com.example.covertervk.data.remote.dto.TrackId

interface Repository {
    suspend fun getTrackById(id: String):TrackId
    suspend fun getChart():MusicChart
}