package com.example.covertervk.data.remote


import com.example.covertervk.data.remote.dto.MusicChart
import com.example.covertervk.data.remote.dto.TrackId
import com.example.covertervk.data.remote.dto.ValueDto
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface Api {
    @GET("/v6/55c8d6da7a2846fa4a40ca29/pair/{fromValue}/{toValue}/{amount}")

    suspend fun getExchange(
        @Path("fromValue") fromValue: String,
        @Path("toValue") toValue: String,
        @Path("amount") amount: String
    ): ValueDto

    @GET("/track/{id}")

    suspend fun getTrackById(
        @Path("id") id: Int
    ): TrackId

    @GET("/chart/0")

    suspend fun getCharts(): MusicChart
}
