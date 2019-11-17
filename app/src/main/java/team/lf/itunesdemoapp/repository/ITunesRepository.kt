package team.lf.itunesdemoapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import team.lf.itunesdemoapp.database.DatabaseModel
import team.lf.itunesdemoapp.database.ITunesDemoDatabase
import team.lf.itunesdemoapp.database.asDomainModel
import team.lf.itunesdemoapp.database.asDomainTrackModel
import team.lf.itunesdemoapp.domain.DomainModel
import team.lf.itunesdemoapp.network.ITunesApi
import team.lf.itunesdemoapp.network.NetworkContainer
import team.lf.itunesdemoapp.network.asDBModel
import team.lf.itunesdemoapp.network.asDatabaseModel

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


    suspend fun getAndSaveNetworkContainer(wrapperType: String, id: String) {
        withContext(Dispatchers.IO) {
            val lookupList = when (wrapperType) {
                "collection" -> {

                    ITunesApi.retrofitService.getTracksByCollectionIdAsync(id).await()
                }
                "track" -> {
                    ITunesApi.retrofitService.getTracksByCollectionIdAsync(id).await()
                } //todo метод для посика инфо по трэку, если нужен
                else -> {
                    ITunesApi.retrofitService.getTracksByCollectionIdAsync(id).await()
                } //todo метод для поиска инфы по исполнителю
            }
            lookupList.asDatabaseModel().map {
                when (it) {
                    is DatabaseModel.Collection -> database.iTunesDemoDao.insertCollection(it)
                    is DatabaseModel.Track -> database.iTunesDemoDao.insertTrack(it)
                    is DatabaseModel.Artist -> database.iTunesDemoDao.insertArtist(it)
                }
            }

        }
    }


    suspend fun clearDatabase() {
        withContext(Dispatchers.IO) {
            database.iTunesDemoDao.clear()
        }
    }

    fun getTracksByCollectionId(id: String): LiveData<List<DomainModel.Track>> {
        return Transformations.map(database.iTunesDemoDao.getTracksByCollectionsId(id)){
            it.asDomainTrackModel()
        }
    }
}