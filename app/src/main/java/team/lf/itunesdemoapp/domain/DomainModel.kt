package team.lf.itunesdemoapp.domain


sealed class DomainModel {
    data class Collection(
        val artistId: Long,
        val artistName: String,
        val artistViewUrl: String,
        val artworkUrl100: String,
        val artworkUrl60: String,
        override val id: Long,
        val collectionName: String,
        val collectionPrice: Double,
        val collectionType: String,
        val collectionViewUrl: String,
        val copyright: String,
        val country: String,
        val currency: String,
        val primaryGenreName: String,
        val releaseDate: String,
        val trackCount: Int
    ) : DomainModel()

    data class Track(
        val artistId: Long,
        val artistName: String,
        val artistViewUrl: String,
        val artworkUrl100: String,
        val artworkUrl30: String,
        val artworkUrl60: String,
        val collectionId: Long,
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
        override val id: Long,
        val trackName: String,
        val trackNumber: Int,
        val trackPrice: Double,
        val trackTimeMillis: Long,
        val trackViewUrl: String
    ) : DomainModel()

    data class Artist(
        override val id: Long,
        val artistLinkUrl: String,
        val artistName: String,
        val primaryGenreName: String
    ) : DomainModel()

    object Header: DomainModel() {
        override val id = Long.MIN_VALUE
    }

    abstract val id: Long
}



