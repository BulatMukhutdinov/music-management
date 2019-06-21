package tat.mukhutdinov.musicmanagement.albumstop.ui.boundary

import tat.mukhutdinov.musicmanagement.album.model.Album

interface SaveAlbumUseCase {

    suspend fun execute(album: Album)
}