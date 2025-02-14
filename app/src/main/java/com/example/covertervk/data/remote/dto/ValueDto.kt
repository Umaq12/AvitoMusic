package com.example.covertervk.data.remote.dto

import com.example.covertervk.domain.model.AlbumDomain
import com.example.covertervk.domain.model.ArtistDomain
import com.example.covertervk.domain.model.ContributorDomain
import com.example.covertervk.domain.model.MusicChartDomain
import com.example.covertervk.domain.model.PlaylistDomain
import com.example.covertervk.domain.model.PodcastDomain
import com.example.covertervk.domain.model.TrackDomain

data class ValueDto(
    val base_code: String,
    val conversion_rate: Double,
    val conversion_result: Double,
    val documentation: String,
    val result: String,
    val target_code: String,
    val terms_of_use: String,
    val time_last_update_unix: Int,
    val time_last_update_utc: String,
    val time_next_update_unix: Int,
    val time_next_update_utc: String
)

data class MusicChart(
    val albums: Albums,
    val artists: Artists,
    val playlists: Playlists,
    val podcasts: Podcasts,
    val tracks: Tracks
)

data class Albums(
    val `data`: List<Data>,
    val total: Int
)

data class Artists(
    val `data`: List<DataX>,
    val total: Int
)

data class Playlists(
    val `data`: List<DataXX>,
    val total: Int
)

data class Podcasts(
    val `data`: List<DataXXX>,
    val total: Int
)

data class Tracks(
    val `data`: List<DataXXXX>,
    val total: Int
)

data class Data(
    val artist: Artist,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val id: Int,
    val link: String,
    val md5_image: String,
    val position: Int,
    val record_type: String,
    val title: String,
    val tracklist: String,
    val type: String
)

data class Artist(
    val id: Int,
    val link: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val radio: Boolean,
    val tracklist: String,
    val type: String
)

data class DataX(
    val id: Int,
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
    val `public`: Boolean,
    val title: String,
    val tracklist: String,
    val type: String,
    val user: User
)

data class User(
    val id: Long,
    val name: String,
    val tracklist: String,
    val type: String
)

data class DataXXX(
    val available: Boolean,
    val description: String,
    val fans: Int,
    val id: Int,
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

data class DataXXXX(
    val album: Album,
    val artist: Artist,
    val duration: Int,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: Int,
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

data class Album(
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val id: Int,
    val md5_image: String,
    val title: String,
    val tracklist: String,
    val type: String
)

fun MusicChart.toDomain(): MusicChartDomain {
    return MusicChartDomain(
        albums = albums.data.map { it.toDomain() },
        artists = artists.data.map { it.toDomain() },
        playlists = playlists.data.map { it.toDomain() },
        podcasts = podcasts.data.map { it.toDomain() },
        tracks = tracks.data.map { it.toDomain() }
    )
}


fun Data.toDomain(): AlbumDomain {
    return AlbumDomain(
        id = id,
        title = title,
        coverUrl = cover_xl,
        artist = artist.toDomain()
    )
}

fun Artist.toDomain(): ArtistDomain {
    return ArtistDomain(
        id = id,
        name = name,
        pictureUrl = picture_xl
    )
}
fun DataX.toDomain(): ArtistDomain {
    return ArtistDomain(
        id = id,
        name = name,
        pictureUrl = picture_xl
    )
}

fun DataXX.toDomain(): PlaylistDomain {
    return PlaylistDomain(
        id = id,
        title = title,
        trackCount = nb_tracks,
        coverUrl = picture_xl
    )
}

fun DataXXX.toDomain(): PodcastDomain {
    return PodcastDomain(
        id = id,
        title = title,
        description = description,
        coverUrl = picture_xl
    )
}

fun DataXXXX.toDomain(): TrackDomain {
    return TrackDomain(
        id = id,
        title = title,
        duration = duration,
        rank = rank,
        previewUrl = preview,
        artist = artist.toDomain(),
        album = album.toDomain()
    )
}
fun Album.toDomain(): AlbumDomain {
    return AlbumDomain(
        id = id,
        title = title,
        coverUrl = cover_xl,
        artist = ArtistDomain(id = 0, name = "Unknown", pictureUrl = "")
    )
}
//fun ValueDto.toValue(): Value {
//    return Value(
//        base_code = base_code,
//        conversion_rate = conversion_rate,
//        conversion_result = conversion_result,
//        result = result,
//        target_code = target_code
//    )
//}

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
    val id: Int,
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

data class Contributor(
    val id: Int,
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
fun TrackId.toDomain(): TrackDomain {
    return TrackDomain(
        id = id,
        title = title,
        duration = duration,
        rank = rank,
        previewUrl = preview,
        artist = artist.toDomain(),
        album = album.toDomain()
    )
}
fun Contributor.toDomain(): ContributorDomain {
    return ContributorDomain(
        id = id,
        name = name,
        pictureUrl = picture_xl,
        role = role
    )
}
