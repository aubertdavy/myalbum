package fr.daubert.albumapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album(
    @PrimaryKey(autoGenerate = false)
    val albumId: Int,
    val id: Int,
    val title: String = "New album",
    val url: String = "",
    val thumbnailUrl: String = ""
)