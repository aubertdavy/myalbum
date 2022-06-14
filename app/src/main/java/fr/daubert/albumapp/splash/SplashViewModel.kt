package fr.daubert.albumapp.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject internal constructor() : ViewModel() {

    private val _onGoTo = MutableLiveData<Boolean>()
    val onGoTo = _onGoTo

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            delay(3000)
            _onGoTo.value = true
        }
    }
}