package team.lf.itunesdemoapp.ui.lookup

import android.app.Application
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.lifecycle.*
import kotlinx.coroutines.*
import team.lf.itunesdemoapp.database.getDatabase
import team.lf.itunesdemoapp.domain.DomainModel
import team.lf.itunesdemoapp.repository.ITunesRepository
import timber.log.Timber
import java.io.IOException

class LookupViewModel(application: Application, val collection: DomainModel.Collection): AndroidViewModel(application) {

    private val repository = ITunesRepository(getDatabase(application.applicationContext))
    val trackList: LiveData<List<DomainModel.Track>> = repository.getTracksByCollectionId(collection.id)
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    //todo networkError logic in UI
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        getLookupNetworkContainer(collection.wrapperType, collection)
    }

    private fun getLookupNetworkContainer(wrapperType: String, collection: DomainModel.Collection) {
        Timber.d(trackList.value.toString())
        viewModelScope.launch {
            try {
                if(!repository.checkTrackCount(collection.id)){
                    repository.getAndSaveNetworkContainer(wrapperType, collection.id)
                    Timber.d("getLookupNetworkContainer")
                }
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                _eventNetworkError.value = true
            }
        }
    }



    fun onTrackPlayPressed(track: DomainModel.Track?){
        viewModelScope.launch {
            try {
                if(track!= null){
                    Timber.d("onTrackPlay")
                    repository.updatePlayingStateOfTrack(track.id, trackList.value!!)
                } else{
                    repository.updatePlayingStateOfTrack("", trackList.value!!)
                }



            }catch (networkError: IOException) {
                _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onTrackPlayingComplete() {
        onTrackPlayPressed(null)
    }

    class Factory(private val app: Application, private val collection:DomainModel.Collection) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LookupViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LookupViewModel(app, collection) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }


}