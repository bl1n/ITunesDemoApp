package team.lf.itunesdemoapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import team.lf.itunesdemoapp.database.*
import team.lf.itunesdemoapp.domain.DomainModel
import team.lf.itunesdemoapp.network.ITunesApi
import team.lf.itunesdemoapp.network.NetworkContainer
import team.lf.itunesdemoapp.network.asDBModel
import team.lf.itunesdemoapp.network.asDatabaseModel
import timber.log.Timber

class ITunesRepository(private val database: ITunesDemoDatabase) {


    val searchList: LiveData<List<DomainModel>> =
        Transformations.map(database.iTunesDemoDao.getSearchResults()) {
            it.asDomainModel()
        }


    suspend fun refreshSearchList(term: String) {
        withContext(Dispatchers.IO) {
            val searchList: NetworkContainer =
                ITunesApi.retrofitService.getCollectionsByCollectionNameAsync(term).await()
            database.iTunesDemoDao.insertAll(searchList.asDBModel())
        }
    }
    suspend fun checkTrackCount(collectionId: String):Boolean{
        var boolean = false
        withContext(Dispatchers.IO){
            val trackCount = database.iTunesDemoDao.getTracksCountByCollectionId(collectionId)
            boolean = trackCount > 0
            Timber.d(trackCount.toString())
        }
        return boolean
    }


    suspend fun getAndSaveNetworkContainer(wrapperType: String, id: String) {
        withContext(Dispatchers.IO) {
            val lookupList = getLookupListFromDB(wrapperType, id)
            lookupList.asDatabaseModel().map {
                when (it) {
                    is LookupEntity.Collection -> database.iTunesDemoDao.insertCollection(it)
                    is LookupEntity.Track -> database.iTunesDemoDao.insertTrack(it)
                    is LookupEntity.Artist -> database.iTunesDemoDao.insertArtist(it)
                }
            }

        }
    }

    private suspend fun getLookupListFromDB(
        wrapperType: String,
        id: String
    ): NetworkContainer {
        return when (wrapperType) {
            "collection" -> {
                ITunesApi.retrofitService.getTracksByCollectionIdAsync(id).await()
            }
            "track" -> {
                ITunesApi.retrofitService.getTracksByCollectionIdAsync(id).await()
            } //todo метод для поиска инфо по трэку, если нужен
            else -> {
                ITunesApi.retrofitService.getTracksByCollectionIdAsync(id).await()
            } //todo метод для поиска инфы по исполнителю
        }
    }


    suspend fun updatePlayingStateOfTrack(
        trackId: String,
        tracks: List<DomainModel.Track>
    ) {
        withContext(Dispatchers.IO) {
            for (t in tracks) {
                val dbT = database.iTunesDemoDao.getTrack(t.id)
                dbT.isPlaying = false
                database.iTunesDemoDao.updateTrack(dbT)
            }
            if (trackId != "") {
                val dBTrack = database.iTunesDemoDao.getTrack(trackId)
                dBTrack.isPlaying = true
                database.iTunesDemoDao.updateTrack(dBTrack)
            }
        }
    }

    suspend fun clearDatabase() {
        withContext(Dispatchers.IO) {
            database.iTunesDemoDao.clear()
        }
    }

    fun getTracksByCollectionId(id: String): LiveData<List<DomainModel.Track>> {
        return Transformations.map(database.iTunesDemoDao.getTracksByCollectionsId(id)) {
            it.asDomainTrackModel()
        }
    }

    fun getCollectionsByCollectionName(term: String): LiveData<List<DomainModel.Collection>> {
        return Transformations.map(database.iTunesDemoDao.getCollectionsByCollectionName(term)) {
            it.asDomainCollectionModel()
        }
    }
}