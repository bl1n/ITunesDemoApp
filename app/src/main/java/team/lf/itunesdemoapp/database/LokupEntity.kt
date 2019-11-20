package team.lf.itunesdemoapp.database

import android.text.BoringLayout
import androidx.room.Entity
import androidx.room.PrimaryKey
import team.lf.itunesdemoapp.domain.DomainModel

sealed class LookupEntity {
    @Entity(tableName = "collections")
    data class Collection(
        val artistId: String,
        val artistName: String,
        val artistViewUrl: String,
        val artworkUrl100: String,
        val artworkUrl60: String,
        @PrimaryKey val id: String,
        val collectionName: String,
        val collectionPrice: String,
        val collectionType: String,
        val collectionViewUrl: String,
        val copyright: String,
        val country: String,
        val currency: String,
        val primaryGenreName: String,
        val releaseDate: String,
        val trackCount: Int
    ) : LookupEntity()

    @Entity(tableName = "tracks")
    data class Track(
        var isPlaying: Boolean = false,
        val artistId: String,
        val artistName: String,
        val artistViewUrl: String,
        val artworkUrl100: String,
        val artworkUrl30: String,
        val artworkUrl60: String,
        val collectionId: String,
        val collectionName: String,
        val collectionPrice: String,
        val collectionViewUrl: String,
        val country: String,
        val currency: String,
        val isStreamable: Boolean,
        val kind: String,
        val previewUrl: String,
        val primaryGenreName: String,
        val releaseDate: String,
        @PrimaryKey val id: String,
        val trackName: String,
        val trackNumber: Int,
        val trackPrice: String,
        val trackTimeMillis: Long,
        val trackViewUrl: String
    ) : LookupEntity()

    @Entity(tableName = "artists")
    data class Artist(
        @PrimaryKey val id: String,
        val artistLinkUrl: String,
        val artistName: String,
        val primaryGenreName: String
    ) : LookupEntity()
}

fun List<LookupEntity.Track>.asDomainTrackModel():List<DomainModel.Track>{
    return this.map {
        DomainModel.Track(
            artistId = it.artistId,
            artistName = it.artistName,
            artistViewUrl = it.artistViewUrl,
            artworkUrl100 = it.artworkUrl100,
            artworkUrl30 = it.artworkUrl30,
            artworkUrl60 = it.artworkUrl60,
            collectionId = it.collectionId,
            collectionName = it.collectionName,
            collectionPrice = it.collectionPrice,
            collectionViewUrl = it.collectionViewUrl,
            country = it.country,
            currency = it.currency,
            isStreamable = it.isStreamable,
            kind = it.kind,
            previewUrl = it.previewUrl,
            primaryGenreName = it.primaryGenreName,
            releaseDate = it.releaseDate,
            trackName = it.trackName,
            trackNumber = it.trackNumber,
            trackPrice = it.trackPrice,
            trackTimeMillis = it.trackTimeMillis,
            trackViewUrl = it.trackViewUrl,
            id = it.id,
            isPlaying = it.isPlaying
        )
    }
}

fun List<LookupEntity.Collection>.asDomainCollectionModel(): List<DomainModel.Collection>{
    return map {
        DomainModel.Collection(
            artistId = it.artistId,
            artistName = it.artistName,
            artistViewUrl = it.artistViewUrl,
            artworkUrl100 = it.artworkUrl100,
            artworkUrl60 = it.artworkUrl60,
            id = it.id,
            collectionName = it.collectionName,
            collectionPrice = it.collectionPrice,
            collectionType = it.collectionType,
            collectionViewUrl = it.collectionViewUrl,
            copyright = it.copyright,
            country = it.country,
            currency = it.currency,
            primaryGenreName = it.primaryGenreName,
            releaseDate = it.releaseDate,
            trackCount = it.trackCount
        )
    }
}

fun List<LookupEntity.Artist>.asDomainArtistModel():List<DomainModel.Artist>{
    return map {
        DomainModel.Artist(
            id = it.id,
            artistLinkUrl = it.artistLinkUrl,
            artistName = it.artistName,
            primaryGenreName = it.primaryGenreName
        )
    }
}

//fun List<LookupEntity>.asDomainModel():List<DomainModel>{
//    return map {
//        when(it){
//            is LookupEntity.Collection -> DomainModel.Collection(
//                artistId = it.artistId,
//                artistName = it.artistName,
//                artistViewUrl = it.artistViewUrl,
//                artworkUrl100 = it.artworkUrl100,
//                artworkUrl60 = it.artworkUrl60,
//                id = it.id,
//                collectionName = it.collectionName,
//                collectionPrice = it.collectionPrice,
//                collectionType = it.collectionType,
//                collectionViewUrl = it.collectionViewUrl,
//                copyright = it.copyright,
//                country = it.country,
//                currency = it.currency,
//                primaryGenreName = it.primaryGenreName,
//                releaseDate = it.releaseDate,
//                trackCount = it.trackCount
//            )
//            is LookupEntity.Track -> DomainModel.Track(
//                artistId = it.artistId,
//                artistName = it.artistName,
//                artistViewUrl = it.artistViewUrl,
//                artworkUrl100 = it.artworkUrl100,
//                artworkUrl30 = it.artworkUrl30,
//                artworkUrl60 = it.artworkUrl60,
//                collectionId = it.collectionId,
//                collectionName = it.collectionName,
//                collectionPrice = it.collectionPrice,
//                collectionViewUrl = it.collectionViewUrl,
//                country = it.country,
//                currency = it.currency,
//                isStreamable = it.isStreamable,
//                kind = it.kind,
//                previewUrl = it.previewUrl,
//                primaryGenreName = it.primaryGenreName,
//                releaseDate = it.releaseDate,
//                trackName = it.trackName,
//                trackNumber = it.trackNumber,
//                trackPrice = it.trackPrice,
//                trackTimeMillis = it.trackTimeMillis,
//                trackViewUrl = it.trackViewUrl,
//                id = it.id
//            )
//            is LookupEntity.Artist -> DomainModel.Artist(
//                id = it.id,
//                artistLinkUrl = it.artistLinkUrl,
//                artistName = it.artistName,
//                primaryGenreName = it.primaryGenreName
//            )
//        }
//    }
//}
