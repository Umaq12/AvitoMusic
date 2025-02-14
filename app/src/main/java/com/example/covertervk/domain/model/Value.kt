package com.example.covertervk.domain.model

//data class Value(
//    val base_code: String,
//    val conversion_rate: Double,
//    val conversion_result: Double,
//    val result: String,
//    val target_code: String,
//)
data class MusicChartDomain(
    val albums: List<AlbumDomain>,
    val artists: List<ArtistDomain>,
    val playlists: List<PlaylistDomain>,
    val podcasts: List<PodcastDomain>,
    val tracks: List<TrackDomain>,
)

data class AlbumDomain(
    val id: Int,
    val title: String,
    val coverUrl: String,
    val artist: ArtistDomain
)

data class ArtistDomain(
    val id: Int,
    val name: String,
    val pictureUrl: String
)

data class PlaylistDomain(
    val id: Long,
    val title: String,
    val trackCount: Int,
    val coverUrl: String
)

data class PodcastDomain(
    val id: Int,
    val title: String,
    val description: String,
    val coverUrl: String
)

data class TrackDomain(
    val id: Int,
    val title: String,
    val duration: Int,
    val rank: Int,
    val previewUrl: String,
    val artist: ArtistDomain,
    val album: AlbumDomain
)

data class ContributorDomain(
    val id: Int,
    val name: String,
    val pictureUrl: String,
    val role: String
)
