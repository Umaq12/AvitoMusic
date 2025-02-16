package com.example.covertervk.presentation.util

import com.example.covertervk.domain.model.MusicChartDomain
import com.example.covertervk.domain.model.TrackDomain

data class ValueState(
    val isLoading: Boolean = false,
    val track: TrackDomain? = null,
    val chart: MusicChartDomain? = null,
    val error: String = ""
)
