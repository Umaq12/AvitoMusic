package com.example.covertervk.presentation.apiMusicScreen

import android.app.Application
import androidx.annotation.OptIn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import com.example.covertervk.data.remote.Api
import com.example.covertervk.data.remote.dto.toDomain
import com.example.covertervk.domain.util.Constants
import com.example.covertervk.navigation.Screen
import com.example.covertervk.presentation.util.ValueState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiScreenMusicViewModel @Inject constructor(
    private val api: Api,
    savedStateHandle: SavedStateHandle,
    private val context: Application
) : AndroidViewModel(context) {

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    private val _state = mutableStateOf(ValueState())
    val state: State<ValueState> = _state

    private val _currentPosition = MutableStateFlow(0)
    val currentPosition: StateFlow<Int> = _currentPosition

    private val _duration = MutableStateFlow(0)
    val duration: StateFlow<Int> = _duration

    private val exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build()

    init {
        savedStateHandle.get<Long>(Constants.PARAM_ID)?.let { id ->
            getTrackById(id)
        }
        getCharts()
        setupPlayerListeners()
    }

    @OptIn(UnstableApi::class)
    private fun setupPlayerListeners() {
        // Слушатель изменений времени
        exoPlayer.addListener(object : Player.Listener {
            @Deprecated("Deprecated in Java")
            override fun onPositionDiscontinuity(reason: Int) {
                // Отслеживаем текущее время
                _currentPosition.value = exoPlayer.currentPosition.toInt()
                _duration.value = exoPlayer.duration.toInt()
            }

            override fun onPlaybackStateChanged(state: Int) {
                // Проверяем состояние воспроизведения (играет/пауза)
                _isPlaying.value = state == Player.STATE_READY && exoPlayer.isPlaying
            }
        })
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

    fun play(trackUrl: String) {
        val mediaItem = MediaItem.fromUri(trackUrl)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    fun pause() {
        exoPlayer.pause()
    }

    fun nextTrack() {
        val currentTrackIndex = _state.value.chart?.tracks?.indexOfFirst { it.id == exoPlayer.currentMediaItem?.mediaId?.toLong() }
        val nextTrackIndex = currentTrackIndex?.let { it + 1 } ?: 0
        val nextTrack = _state.value.chart?.tracks?.getOrNull(nextTrackIndex)
        nextTrack?.let { play(it.previewUrl) }
    }

    fun previousTrack(navController: NavController) {
        val currentTrackIndex = _state.value.chart?.tracks?.indexOfFirst { it.id == exoPlayer.currentMediaItem?.mediaId?.toLong() }
        val previousTrackIndex = currentTrackIndex?.let { it - 1 } ?: 0
        val previousTrack = _state.value.chart?.tracks?.getOrNull(previousTrackIndex)

        // Если предыдущий трек найден, переходим на его экран по id
        previousTrack?.let {
            navController.navigate(Screen.TrackScreen.createRoute(it.id))
        }
    }


    fun seekTo(position: Int) {
        exoPlayer.seekTo(position.toLong())
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}

