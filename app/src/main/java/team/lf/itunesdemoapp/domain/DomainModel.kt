package team.lf.itunesdemoapp.domain


sealed class DomainModel {
    data class Collection(
        val artistId: String,
        val artistName: String,
        val artistViewUrl: String,
        val artworkUrl100: String,
        val artworkUrl60: String,
        override val id: String,
        val collectionName: String,
        val collectionPrice: String,
        val collectionType: String,
        val collectionViewUrl: String,
        val copyright: String,
        val country: String,
        val currency: String,
        val primaryGenreName: String,
        val releaseDate: String,
        val trackCount: Int,
        override val wrapperType:String = "collection"
    ) : DomainModel()

    data class Track(
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
        override val id: String,
        val trackName: String,
        val trackNumber: Int,
        val trackPrice: String,
        val trackTimeMillis: Long,
        val trackViewUrl: String,
        override val wrapperType:String = "track"
    ) : DomainModel()

    data class Artist(
        override val id: String,
        val artistLinkUrl: String,
        val artistName: String,
        val primaryGenreName: String,
        override val wrapperType:String = "artist"
    ) : DomainModel()

    object Header: DomainModel() {
        override val wrapperType = "header"
        override val id = ""
    }

    abstract val wrapperType:String
    abstract val id: String
}



