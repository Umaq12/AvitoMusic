package com.example.covertervk.data.remote.dto

import com.example.covertervk.domain.model.AlbumDomain
import com.example.covertervk.domain.model.ArtistDomain
import com.example.covertervk.domain.model.ContributorDomain
import com.example.covertervk.domain.model.MusicChartDomain
import com.example.covertervk.domain.model.PlaylistDomain
import com.example.covertervk.domain.model.PodcastDomain
import com.example.covertervk.domain.model.TrackDomain


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