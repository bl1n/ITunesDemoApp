package team.lf.itunesdemoapp.network

import com.squareup.moshi.JsonClass
import team.lf.itunesdemoapp.domain.Artist
import team.lf.itunesdemoapp.domain.DomainCollection
import team.lf.itunesdemoapp.domain.Track

@JsonClass(generateAdapter = true)
data class NetworkContatainer(
    val resultCount: Int,
    val results: List<RichNetworkModel>
)

@JsonClass(generateAdapter = true)
data class RichNetworkModel(
    val wrapperType: String,
    val artistId: Int?,
    val artistName: String?,
    val artistViewUrl: String?,
    val artworkUrl100: String?,
    val artworkUrl60: String?,
    val collectionId: Int?,
    val collectionName: String?,
    val collectionPrice: Double?,
    val collectionType: String?,
    val collectionViewUrl: String?,
    val copyright: String?,
    val country: String?,
    val currency: String?,
    val primaryGenreName: String?,
    val releaseDate: String?,
    val trackCount: Int?,
    val artworkUrl30: String?,
    val isStreamable: Boolean?,
    val kind: String?,
    val previewUrl: String?,
    val trackId: Int?,
    val trackName: String?,
    val trackNumber: Int?,
    val trackPrice: Double?,
    val trackTimeMillis: Int?,
    val trackViewUrl: String?,
    val artistLinkUrl: String?
)

/**
 * Convert Network results to database objects
 */

fun NetworkContatainer.asArtistDomainModel(): List<Artist> {
    val artists = ArrayList<Artist>()
    for (i in this.results.indices) {
        if (this.results[i].wrapperType == "artist") {
            artists.add(
                Artist(
                    artistId = this.results[i].artistId!!,
                    artistLinkUrl = this.results[i].artistLinkUrl!!,
                    artistName = this.results[i].artistName!!,
                    primaryGenreName = this.results[i].primaryGenreName!!,
                    wrapperType = this.results[i].wrapperType
                )
            )
        }
    }
    return artists
}

fun NetworkContatainer.asTrackDomainModel(): List<Track> {
    val tracks = ArrayList<Track>()
    for (i in this.results.indices) {
        if (this.results[i].wrapperType == "track") {
            tracks.add(
                Track(
                    artistId = this.results[i].artistId!!,
                    artistName = this.results[i].artistName!!,
                    artistViewUrl = this.results[i].artistViewUrl!!,
                    artworkUrl100 = this.results[i].artworkUrl100!!,
                    artworkUrl30 = this.results[i].artworkUrl30!!,
                    artworkUrl60 = this.results[i].artworkUrl60!!,
                    collectionId = this.results[i].collectionId!!,
                    collectionName = this.results[i].collectionName!!,
                    collectionPrice = this.results[i].collectionPrice!!,
                    collectionViewUrl = this.results[i].collectionViewUrl!!,
                    country = this.results[i].country!!,
                    currency = this.results[i].currency!!,
                    isStreamable = this.results[i].isStreamable!!,
                    kind = this.results[i].kind!!,
                    previewUrl = this.results[i].previewUrl!!,
                    primaryGenreName = this.results[i].primaryGenreName!!,
                    releaseDate = this.results[i].releaseDate!!,
                    trackId = this.results[i].trackId!!,
                    trackName = this.results[i].trackName!!,
                    trackNumber = this.results[i].trackNumber!!,
                    trackPrice = this.results[i].trackPrice!!,
                    trackTimeMillis = this.results[i].trackTimeMillis!!,
                    trackViewUrl = this.results[i].trackViewUrl!!,
                    wrapperType = this.results[i].wrapperType
                )
            )
        }
    }
    return tracks
}
fun NetworkContatainer.asCollectionDomainModel(): List<DomainCollection> {
    val collections = ArrayList<DomainCollection>()
    for (i in this.results.indices) {
        if (this.results[i].wrapperType == "collection") {
            collections.add(
                DomainCollection(
                    artistId = this.results[i].artistId!!,
                    artistName = this.results[i].artistName!!,
                    artistViewUrl = this.results[i].artistViewUrl!!,
                    artworkUrl100 = this.results[i].artworkUrl100!!,
                    artworkUrl60 = this.results[i].artworkUrl60!!,
                    collectionId = this.results[i].collectionId!!,
                    collectionName = this.results[i].collectionName!!,
                    collectionPrice = this.results[i].collectionPrice!!,
                    collectionViewUrl = this.results[i].collectionViewUrl!!,
                    country = this.results[i].country!!,
                    currency = this.results[i].currency!!,
                    primaryGenreName = this.results[i].primaryGenreName!!,
                    releaseDate = this.results[i].releaseDate!!,
                    wrapperType = this.results[i].wrapperType,
                    collectionType = this.results[i].collectionType!!,
                    copyright = this.results[i].copyright!!,
                    trackCount = this.results[i].trackCount!!
                )
            )
        }
    }
    return collections
}



