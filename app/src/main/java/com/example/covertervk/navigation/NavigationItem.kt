package com.example.covertervk.navigation

sealed class Screen(val route: String, val title: String) {
    object ExchangeScreen : Screen("exchange_screen", "")
    object ApiMusicScreen : Screen("ApiMusicScreen", "")
    object TrackScreen : Screen("track_screen/{trackId}", "") {
        fun createRoute(trackId: Long): String {
            return "track_screen/$trackId"
        }
    }

}
