package com.example.covertervk.presentation.apiMusicScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covertervk.data.remote.Api
import com.example.covertervk.data.remote.dto.toDomain
import com.example.covertervk.domain.util.Constants
import com.example.covertervk.presentation.ValueState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiScreenMusicViewModel @Inject constructor(
    private val api: Api,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ValueState())
    val state: State<ValueState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_ID)?.let { id ->
            getTrackById(id.toString())
        }

        getCharts()
    }

    fun getTrackById(id: String) {
        viewModelScope.launch {
            _state.value = ValueState(isLoading = true)
            try {
                val valueDto = api.getTrackById(id)
                _state.value = ValueState(track = valueDto.toDomain())
            } catch (e: Exception) {
                _state.value = ValueState(error = e.message ?: "An unexpected error occurred")
            }
        }
    }
    private fun getCharts() {
        viewModelScope.launch {
            _state.value = ValueState(isLoading = true)
            try {
                val valueDto = api.getCharts()
                _state.value = ValueState(chart = valueDto.toDomain())
            } catch (e: Exception) {
                _state.value = ValueState(error = e.message ?: "An unexpected error occurred")
            }
        }
    }
}