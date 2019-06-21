package tat.mukhutdinov.musicmanagement.albumssaved.ui.boundary

import tat.mukhutdinov.musicmanagement.album.model.Album

interface DeleteAlbumUseCase {

    suspend fun execute(album: Album)
}