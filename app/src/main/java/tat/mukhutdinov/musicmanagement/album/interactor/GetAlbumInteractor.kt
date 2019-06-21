package tat.mukhutdinov.musicmanagement.album.interactor

import tat.mukhutdinov.musicmanagement.album.interactor.boundary.AlbumGateway
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.album.ui.boundary.GetAlbumUseCase

class GetAlbumInteractor(private val gateway: AlbumGateway) : GetAlbumUseCase {

    override suspend fun execute(albumName: String, artistName: String): Album =
        gateway.get(albumName, artistName)
}