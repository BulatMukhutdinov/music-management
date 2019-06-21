package tat.mukhutdinov.musicmanagement.albumstop.ui.boundary

import tat.mukhutdinov.musicmanagement.album.model.Album

interface GetTopAlbumsUseCase {

    suspend fun execute(artistName: String): List<Album>
}