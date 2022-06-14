package fr.daubert.albumapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums ORDER BY albumId")
    fun getAll(): List<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: List<Album>)

    @Delete
    fun delete(album: Album)

    @Delete
    fun deleteAll(albums: List<Album>)
}