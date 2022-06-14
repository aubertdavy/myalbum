package fr.daubert.albumapp

import fr.daubert.albumapp.data.Album
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AlbumTest {

    private lateinit var album: Album

    @Before
    fun setUp() {
        album = Album(
            albumId = 10,
            id = 1,
            title = "A super title",
            url = "ttps://via.placeholder.com/600/92c952",
            thumbnailUrl = "https://via.placeholder.com/150/92c952"
        )
    }

    @Test
    fun test_default_values() {
        val defaultAlbum = Album(11, 3)
        Assert.assertEquals("New album", defaultAlbum.title)
        Assert.assertEquals("", defaultAlbum.url)
        Assert.assertEquals("", defaultAlbum.thumbnailUrl)
    }

    @Test
    fun test_real_values() {
        val defaultAlbum = Album(11, 3, url = "ttps://via.placeholder.com/600/92c952")
        Assert.assertNotEquals(defaultAlbum.id, album.id)
        Assert.assertNotEquals(defaultAlbum.albumId, album.albumId)
        Assert.assertNotEquals(defaultAlbum.title, album.title)
        Assert.assertNotEquals(defaultAlbum.thumbnailUrl, album.thumbnailUrl)
        Assert.assertEquals(defaultAlbum.url, album.url)
    }
}