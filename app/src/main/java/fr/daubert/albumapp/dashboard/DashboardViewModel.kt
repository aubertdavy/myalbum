package fr.daubert.albumapp.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.daubert.albumapp.data.Album
import fr.daubert.albumapp.data.AlbumRepository
import fr.daubert.albumapp.data.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject internal constructor(
    private val repository: AlbumRepository
) : ViewModel() {

    private val _albums = MutableLiveData<Result<List<Album>>>()
    val albums = _albums

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            repository.fetchAlbums().collect {
                _albums.value = it
            }
        }
    }
}