package com.example.covertervk.data.remote.dto

import com.example.covertervk.domain.model.AlbumDomain
import com.example.covertervk.domain.model.ArtistDomain
import com.example.covertervk.domain.model.ContributorDomain
import com.example.covertervk.domain.model.MusicChartDomain
import com.example.covertervk.domain.model.PlaylistDomain
import com.example.covertervk.domain.model.PodcastDomain
import com.example.covertervk.domain.model.TrackDomain
import kotlinx.serialization.Serializable


@Serializable
data class MusicChart(
    val albums: Albums,
    val artists: Artists,
    val playlists: Playlists,
    val podcasts: Podcasts,
    val tracks: Tracks
)

@Serializable
data class Albums(
    val data: List<Data>,
    val total: Int
)

@Serializable
data class Artists(
    val data: List<DataX>,
    val total: Int
)

@Serializable
data class Playlists(
    val data: List<DataXX>,
    val total: Int
)

@Serializable
data class Podcasts(
    val data: List<DataXXX>,
    val total: Int
)

@Serializable
data class Tracks(
    val data: List<DataXXXX>,
    val total: Int
)

@Serializable
data class Data(
    val artist: Artist,
    val cover: String? = null,
    val cover_big: String? = null,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val id: Long,
    val link: String,
    val md5_image: String,
    val position: Int,
    val record_type: String,
    val title: String,
    val tracklist: String,
    val type: String
)

@Serializable
data class Artist(
    val id: Long? = null,
    val link: String? = null,
    val name: String? = null,
    val picture: String? = null,
    val picture_big: String? = null,
    val picture_medium: String? = null,
    val picture_small: String? = null,
    val picture_xl: String? = null,
    val radio: Boolean? = null,
    val tracklist: String? = null,
    val type: String? = null
)

@Serializable
data class DataX(
    val id: Long,
    val link: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val position: Int,
    val radio: Boolean,
    val tracklist: String,
    val type: String
)

@Serializable
data class DataXX(
    val checksum: String,
    val creation_date: String,
    val id: Long,
    val link: String,
    val md5_image: String,
    val nb_tracks: Int,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_type: String,
    val picture_xl: String,
    val public: Boolean,
    val title: String,
    val tracklist: String,
    val type: String,
    val user: User
)

@Serializable
data class User(
    val id: Long,
    val name: String,
    val tracklist: String,
    val type: String
)

@Serializable
data class DataXXX(
    val available: Boolean,
    val description: String,
    val fans: Int,
    val id: Long,
    val link: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val share: String,
    val title: String,
    val type: String
)

@Serializable
data class DataXXXX(
    val album: Album,
    val artist: Artist,
    val duration: Int,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: Long,
    val link: String,
    val md5_image: String,
    val position: Int,
    val preview: String,
    val rank: Int,
    val title: String,
    val title_short: String,
    val title_version: String,
    val type: String
)

@Serializable
data class Album(
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val id: Long,
    val md5_image: String,
    val title: String,
    val tracklist: String,
    val type: String
)


@Serializable
data class TrackId(
    val album: Album,
    val artist: Artist,
    val available_countries: List<String>,
    val bpm: Int,
    val contributors: List<Contributor>,
    val disk_number: Int,
    val duration: Int,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val gain: Double,
    val id: Long,
    val isrc: String,
    val link: String,
    val md5_image: String,
    val preview: String,
    val rank: Int,
    val readable: Boolean,
    val release_date: String,
    val share: String,
    val title: String,
    val title_short: String,
    val title_version: String,
    val track_position: Int,
    val track_token: String,
    val type: String
)

@Serializable
data class Contributor(
    val id: Long,
    val link: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val radio: Boolean,
    val role: String,
    val share: String,
    val tracklist: String,
    val type: String
)

