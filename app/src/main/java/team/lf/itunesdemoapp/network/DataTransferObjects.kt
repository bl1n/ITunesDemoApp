package team.lf.itunesdemoapp.network

import com.squareup.moshi.JsonClass
import team.lf.itunesdemoapp.database.DBSearchResult

@JsonClass(generateAdapter = true)
data class NetworkContainer(
    val resultCount: Int,
    val results: List<NetSearchResult>
)

@JsonClass(generateAdapter = true)
data class NetSearchResult(
    val wrapperType: String,
    val artistId: Long?,
    val artistName: String?,
    val artistViewUrl: String?,
    val artworkUrl100: String?,
    val artworkUrl60: String?,
    val collectionId: Long?,
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
    val trackId: Long?,
    val trackName: String?,
    val trackNumber: Int?,
    val trackPrice: Double?,
    val trackTimeMillis: Long?,
    val trackViewUrl: String?,
    val artistLinkUrl: String?
)

fun NetworkContainer.asDBModel() :List<DBSearchResult>{
    return this.results.map {
        DBSearchResult(
            wrapperType = it.wrapperType,
            artistId = it.artistId,
            artistName = it.artistName,
            artistViewUrl = it.artistViewUrl,
            artworkUrl100 =  it.artworkUrl100,
            artworkUrl60 = it.artworkUrl60,
            collectionId = it.collectionId,
            collectionName = it.collectionName,
            collectionPrice = it.collectionPrice,
            collectionType = it.collectionType,
            collectionViewUrl = it.collectionViewUrl,
            copyright = it.copyright,
            country = it.country,
            currency = it.currency,
            primaryGenreName = it.primaryGenreName,
            releaseDate = it.releaseDate,
            trackCount = it.trackCount,
            artworkUrl30 = it.artworkUrl30,
            isStreamable = it.isStreamable,
            kind = it.kind,
            previewUrl = it.previewUrl,
            trackId = it.trackId,
            trackNumber = it.trackNumber,
            trackName = it.trackName,
            trackPrice = it.trackPrice,
            trackTimeMillis = it.trackTimeMillis,
            trackViewUrl = it.trackViewUrl,
            artistLinkUrl = it.artistLinkUrl
        )
    }
}



