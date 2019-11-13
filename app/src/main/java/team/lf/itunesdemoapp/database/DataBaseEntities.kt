package team.lf.itunesdemoapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import team.lf.itunesdemoapp.domain.DomainCollection


@Entity
data class DataBaseCollection constructor(
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl60: String,
    val collectionId: Int,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionType: String,
    @PrimaryKey val collectionViewUrl: String,
    val copyright: String,
    val country: String,
    val currency: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCount: Int,
    val wrapperType: String
)
data class Track(
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val collectionId: Int,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionViewUrl: String,
    val country: String,
    val currency: String,
    val isStreamable: Boolean,
    val kind: String,
    val previewUrl: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackId: Int,
    val trackName: String,
    val trackNumber: Int,
    val trackPrice: Double,
    val trackTimeMillis: Int,
    val trackViewUrl: String,
    val wrapperType: String
)

data class Artist(
    val amgArtistId: Int,
    val artistId: Int,
    val artistLinkUrl: String,
    val artistName: String,
    val artistType: String,
    val primaryGenreName: String,
    val wrapperType: String
)

fun List<DataBaseCollection>.asCollectionDomainModel(): List<DomainCollection> {
    return map {
        DomainCollection(
            artistId = it.artistId,
            artistName = it.artistName,
            artistViewUrl = it.artistViewUrl,
            artworkUrl100 = it.artworkUrl100,
            artworkUrl60 = it.artworkUrl60,
            collectionId = it.collectionId,
            collectionName = it.collectionName,
            collectionPrice = it.collectionPrice,
            collectionViewUrl = it.collectionViewUrl,
            country = it.country,
            currency = it.currency,
            primaryGenreName = it.primaryGenreName,
            releaseDate = it.releaseDate,
            wrapperType = it.wrapperType,
            collectionType = it.collectionType,
            copyright = it.copyright,
            trackCount = it.trackCount
        )
    }





}
