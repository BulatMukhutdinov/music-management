package tat.mukhutdinov.musicmanagement.albumssaved.interactor

import tat.mukhutdinov.musicmanagement.album.interactor.boundary.AlbumGateway
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumssaved.ui.boundary.DeleteAlbumUseCase

class DeleteAlbumInteractor(private val gateway: AlbumGateway) : DeleteAlbumUseCase {

    override suspend fun execute(album: Album) =
        gateway.delete(album)
}