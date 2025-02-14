package com.example.covertervk.domain.repository

import com.example.covertervk.data.remote.dto.MusicChart
import com.example.covertervk.data.remote.dto.TrackId
import com.example.covertervk.data.remote.dto.ValueDto

interface Repository {
    suspend fun getExchange(fromValue: String, toValue: String, amount: String): ValueDto
    suspend fun getTrackById(id: Int):TrackId
    suspend fun getChart():MusicChart
}