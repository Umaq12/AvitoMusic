package com.example.covertervk.domain.model


data class MusicChartDomain(
    val albums: List<AlbumDomain>,
    val artists: List<ArtistDomain>,
    val playlists: List<PlaylistDomain>,
    val podcasts: List<PodcastDomain>,
    val tracks: List<TrackDomain>,
)

data class AlbumDomain(
    val id: Long? = null,
    val title: String? = null,
    val coverUrl: String? = null,
    val artist: ArtistDomain
)

data class ArtistDomain(
    val id: Long? = null,
    val name: String? = null,
    val pictureUrl: String? = null
)

data class PlaylistDomain(
    val id: Long,
    val title: String,
    val trackCount: Int,
    val coverUrl: String
)

data class PodcastDomain(
    val id: Long,
    val title: String,
    val description: String,
    val coverUrl: String
)

data class TrackDomain(
    val id: Long,
    val title: String,
    val duration: Int,
    val rank: Int,
    val previewUrl: String,
    val artist: ArtistDomain,
    val album: AlbumDomain
)

data class ContributorDomain(
    val id: Long,
    val name: String,
    val pictureUrl: String,
    val role: String
)
