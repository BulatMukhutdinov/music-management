package tat.mukhutdinov.musicmanagement.album.ui.boundary

import tat.mukhutdinov.musicmanagement.album.model.Album

interface GetAlbumUseCase {

    suspend fun execute(albumName: String, artistName: String): Album
}