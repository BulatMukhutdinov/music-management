package tat.mukhutdinov.musicmanagement.artistserarch.interactor.boundary

import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist

interface ArtistGateway {

    suspend fun searchArtists(artist: String): List<Artist>
}