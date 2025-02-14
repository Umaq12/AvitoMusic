package com.example.covertervk.data.repository

import com.example.covertervk.data.remote.Api
import com.example.covertervk.data.remote.dto.MusicChart
import com.example.covertervk.data.remote.dto.TrackId
import com.example.covertervk.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api:Api
): Repository {

    override suspend fun getTrackById(id: Int): TrackId {
        return api.getTrackById(id)
    }

    override suspend fun getChart(): MusicChart {
        return api.getCharts()
    }
}