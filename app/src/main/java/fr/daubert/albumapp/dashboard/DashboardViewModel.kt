package fr.daubert.albumapp.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.daubert.albumapp.data.Album
import fr.daubert.albumapp.data.AlbumRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject internal constructor(
    private val repository: AlbumRepository
) : ViewModel() {

    enum class ScreenMode {
        LOADING, NONE
    }

    data class State(
        val albums: List<Album> = emptyList(),
        val mode: ScreenMode = ScreenMode.NONE
    )

    private val _state = MutableLiveData<State>()
    val state = _state

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            _state.postValue(State(mode = ScreenMode.LOADING))
            repository.getAlbums().collect {
                _state.postValue(State(
                    mode = ScreenMode.NONE,
                    albums = it ?: emptyList()
                ))
            }
        }
    }
}