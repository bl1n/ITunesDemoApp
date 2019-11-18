package team.lf.itunesdemoapp.ui.search

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

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ITunesRepository(getDatabase(application.applicationContext))

    val searchList = repository.searchList

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown



    init {
        refreshSearchListFromRepository("")
    }

    fun refreshSearchListFromRepository(term: String) {
        if (term != ""){
            viewModelScope.launch {
                try {
                    Timber.d("refresh")
                    repository.clearDatabase()
                    repository.refreshSearchList(term)
                    _eventNetworkError.value = false
                    _isNetworkErrorShown.value = false

                } catch (networkError: IOException) {
                    _eventNetworkError.value = true
                }
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

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}