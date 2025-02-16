package com.example.covertervk.presentation.trackScreen

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
class TrackScreenViewModel @Inject constructor(
    private val api: Api,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(ValueState())
    val state: State<ValueState> = _state

    init {
        savedStateHandle.get<Long>(Constants.PARAM_ID)?.let { id ->
            getTrackById(id)
        }
    }

    fun getTrackById(id: Long) {
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
}