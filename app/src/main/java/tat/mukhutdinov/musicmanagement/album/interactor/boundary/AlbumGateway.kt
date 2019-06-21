package tat.mukhutdinov.musicmanagement.album.interactor.boundary

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import tat.mukhutdinov.musicmanagement.album.model.Album

interface AlbumGateway {

    fun getAllLocal(): LiveData<PagedList<Album>>

    suspend fun getTopOfArtist(artistName: String): List<Album>

    suspend fun get(albumName: String, artistName: String): Album

    suspend fun save(album: Album)

    suspend fun delete(album: Album)
}