package team.lf.itunesdemoapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import team.lf.itunesdemoapp.database.ITunesDemoDatabase
import team.lf.itunesdemoapp.database.asDomainModel
import team.lf.itunesdemoapp.domain.DomainModel
import team.lf.itunesdemoapp.network.ITunesApi
import team.lf.itunesdemoapp.network.NetworkContainer
import team.lf.itunesdemoapp.network.asDBModel

class ITunesRepository(private val database: ITunesDemoDatabase) {

    val searchList: LiveData<List<DomainModel>>  =
        Transformations.map(database.iTunesDemoDao.getSearchResults()){
        it.asDomainModel()
    }

    suspend fun refreshSearchList(term: String){
        withContext(Dispatchers.IO){
            val searchList: NetworkContainer = ITunesApi.retrofitService.getCollectionsByColeectionName(term).await()
            database.iTunesDemoDao.insertAll(searchList.asDBModel())
        }
    }

    suspend fun clearDatabase(){
        withContext(Dispatchers.IO){
            database.iTunesDemoDao.clear()
        }
    }
}