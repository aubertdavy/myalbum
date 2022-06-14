package fr.daubert.albumapp.data

import android.accounts.NetworkErrorException
import fr.daubert.albumapp.api.AlbumApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val service: AlbumApi,
    private val albumDao: AlbumDao) {

    suspend fun fetchAlbums(): Flow<Result<List<Album>>?> {
        return flow {
            emit(getAlbums())
            emit(Result.loading())
            val result = fetchResultAlbums()

            if (result.status == Result.Status.SUCCESS) {
                result.data?.let { it ->
                    albumDao.deleteAll(it)
                    albumDao.insertAll(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun fetchResultAlbums(): Result<List<Album>> =
        getResponse(request = { service.fetchAlbums() })

   private fun getAlbums(): Result<List<Album>>? =
        albumDao.getAll()?.let {
            Result.success(it)
        }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                throw NetworkErrorException("Error when api called")
            }
        } catch (e: Throwable) {
            throw e
        }
    }
}