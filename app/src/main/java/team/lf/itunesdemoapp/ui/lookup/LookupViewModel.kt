package team.lf.itunesdemoapp.ui.lookup

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import team.lf.itunesdemoapp.database.getDatabase
import team.lf.itunesdemoapp.domain.DomainModel
import team.lf.itunesdemoapp.repository.ITunesRepository
import timber.log.Timber
import java.io.IOException

class LookupViewModel(application: Application, collection: DomainModel.Collection): AndroidViewModel(application) {

    private val repository = ITunesRepository(getDatabase(application.applicationContext))
    val trackList = repository.getTracksByCollectionId(collection.id)
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        getLookupNetworkContainer(collection.wrapperType, collection.id)
    }

    private fun getLookupNetworkContainer(wrapperType: String, id: String) {
        viewModelScope.launch {
            try {
                Timber.d("getLookupNetworkContainer")
                repository.getAndSaveNetworkContainer(wrapperType, id)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
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