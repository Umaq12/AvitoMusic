package com.example.covertervk.navigation

sealed class Screen(val route: String, val title: String) {
    object ExchangeScreen : Screen("exchange_screen", "Exchange")
    object ApiMusicScreen : Screen("ApiMusicScreen", "ApiMusicScreen")
    object TrackScreen : Screen("track_screen/{trackId}", "1") {
        fun createRoute(trackId: Long): String {
            return "track_screen/$trackId"
        }
    }

}
