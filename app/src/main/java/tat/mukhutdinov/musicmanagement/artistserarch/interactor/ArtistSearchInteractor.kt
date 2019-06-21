package tat.mukhutdinov.musicmanagement.artistserarch.interactor

import tat.mukhutdinov.musicmanagement.artistserarch.interactor.boundary.ArtistGateway
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist
import tat.mukhutdinov.musicmanagement.artistserarch.ui.boundary.ArtistSearchUseCase

class ArtistSearchInteractor(private val gateway: ArtistGateway) : ArtistSearchUseCase {

    override suspend fun execute(query: String): List<Artist> =
        if (query.isBlank()) {
            emptyList()
        } else {
            gateway.searchArtists(query)
        }
}