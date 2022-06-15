package fr.daubert.albumapp.data

import android.util.Log
import fr.daubert.albumapp.api.AlbumApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val service: AlbumApi,
    private val albumDao: AlbumDao) {

    suspend fun getAlbums(): Flow<List<Album>?> {
        return flow {
            emit(albumDao.getAll())

            try {
                val response = service.fetchAlbums()
                if (response.isSuccessful) {
                    val albums = response.body()
                    albumDao.deleteAll()
                    albumDao.insertAll(albums as List<Album>)
                    emit(albums)
                }
            } catch (e: Throwable) {
                Log.e(AlbumRepository::class.simpleName, "Error during fetching albums ${e.message}")
            }

        }.flowOn(Dispatchers.IO)
    }
}