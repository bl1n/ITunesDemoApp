package team.lf.itunesdemoapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import team.lf.itunesdemoapp.domain.DomainModel
import kotlin.text.Typography.copyright


@Entity
data class DBSearchResult constructor(
    @PrimaryKey(autoGenerate = true) var searchId: Long = 0L,
    val wrapperType: String,
    val artistId: String?,
    val artistName: String?,
    val artistViewUrl: String?,
    val artworkUrl100: String?,
    val artworkUrl60: String?,
    val collectionId: String?,
    val collectionName: String?,
    val collectionPrice: String?,
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
    val trackId: String?,
    val trackName: String?,
    val trackNumber: Int?,
    val trackPrice: String?,
    val trackTimeMillis: Long?,
    val trackViewUrl: String?,
    val artistLinkUrl: String?
)

fun List<DBSearchResult>.asDomainModel():List<DomainModel>{
    return map {
        when(it.wrapperType){
            "track" -> DomainModel.Track(
                artistId = it.artistId!!,
                artistName = it.artistName!!,
                artistViewUrl = it.artistViewUrl!!,
                artworkUrl100 = it.artworkUrl100!!,
                artworkUrl30 = it.artworkUrl30!!,
                artworkUrl60 = it.artworkUrl60!!,
                collectionId = it.collectionId!!,
                collectionName = it.collectionName!!,
                collectionPrice = it.collectionPrice!!,
                collectionViewUrl = it.collectionViewUrl!!,
                country = it.country!!,
                currency = it.currency!!,
                isStreamable = it.isStreamable!!,
                kind = it.kind!!,
                previewUrl = it.previewUrl!!,
                primaryGenreName = it.primaryGenreName!!,
                releaseDate = it.releaseDate!!,
                id = it.trackId!!,
                trackName = it.trackName!!,
                trackNumber = it.trackNumber!!,
                trackPrice = it.trackPrice!!,
                trackTimeMillis = it.trackTimeMillis!!,
                trackViewUrl = it.trackViewUrl!!,
                isPlaying = false
            )
            "collection" -> DomainModel.Collection(
                artistId = it.artistId!!,
                artistName = it.artistName!!,
                artistViewUrl = it.artistViewUrl?:"",
                artworkUrl100 = it.artworkUrl100!!,
                artworkUrl60 = it.artworkUrl60!!,
                id = it.collectionId!!,
                collectionName = it.collectionName!!,
                collectionPrice = it.collectionPrice?:"",
                collectionType = it.collectionType!!,
                collectionViewUrl = it.collectionViewUrl!!,
                copyright = it.copyright!!,
                country = it.country!!,
                currency = it.currency!!,
                primaryGenreName = it.primaryGenreName!!,
                releaseDate = it.releaseDate!!,
                trackCount = it.trackCount!!
            )
            else-> DomainModel.Artist(
                id = it.artistId!!,
                artistLinkUrl = it.artistLinkUrl!!,
                artistName = it.artistName!!,
                primaryGenreName = it.primaryGenreName!!
            )
        }
    }
}

