package com.example.covertervk.data.remote


import com.example.covertervk.data.remote.dto.MusicChart
import com.example.covertervk.data.remote.dto.TrackId
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    

    @GET("track/{id}")

    suspend fun getTrackById(
        @Path("id") id: Long
    ): TrackId

    @GET("chart")

    suspend fun getCharts(): MusicChart
}
