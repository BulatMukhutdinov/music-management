package tat.mukhutdinov.musicmanagement.albumstop.interactor

import tat.mukhutdinov.musicmanagement.album.interactor.boundary.AlbumGateway
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumstop.ui.boundary.SaveAlbumUseCase

class SaveAlbumInteractor(private val gateway: AlbumGateway) : SaveAlbumUseCase {

    override suspend fun execute(album: Album) =
        gateway.save(album)
}