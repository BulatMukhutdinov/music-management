package tat.mukhutdinov.musicmanagement.albumstop.interactor

import tat.mukhutdinov.musicmanagement.album.interactor.boundary.AlbumGateway
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumstop.ui.boundary.GetTopAlbumsUseCase

class GetTopAlbumsInteractor(private val gateway: AlbumGateway) : GetTopAlbumsUseCase {

    override suspend fun execute(artistName: String): List<Album> =
        gateway.getTopOfArtist(artistName)
}