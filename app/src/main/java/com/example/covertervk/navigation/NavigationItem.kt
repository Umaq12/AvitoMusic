package com.example.covertervk.navigation

sealed class Screen(val route: String, val title: String) {
    object ExchangeScreen : Screen("exchange_screen", "Exchange")
    object ApiMusicScreen : Screen("ApiMusicScreen", "ApiMusicScreen")
    object TrackScreen : Screen("track_screen/{trackId}", "Track") {
        fun createRoute(trackId: String) = "track_screen/$trackId"
    }
}
