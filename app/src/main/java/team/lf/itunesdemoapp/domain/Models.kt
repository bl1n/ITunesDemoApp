package team.lf.itunesdemoapp.domain


data class DomainCollection(
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl60: String,
    val collectionId: Int,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionType: String,
    val collectionViewUrl: String,
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
    val artistId: Int,
    val artistLinkUrl: String,
    val artistName: String,
    val primaryGenreName: String,
    val wrapperType: String
)

