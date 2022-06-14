package fr.daubert.albumapp.api

import fr.daubert.albumapp.data.Album
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApi {

    @GET("technical-test.json")
    suspend fun fetchAlbums(): Response<List<Album>>

    companion object {
        const val BASE_URL = "https://static.leboncoin.fr/img/shared/"
    }
}