package tat.mukhutdinov.musicmanagement.artistserarch.gateway

import tat.mukhutdinov.musicmanagement.artistserarch.gateway.boundary.ArtistConverter
import tat.mukhutdinov.musicmanagement.artistserarch.interactor.boundary.ArtistGateway
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist
import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.LastFmApi

class ArtistRemoteGateway(
    private val api: LastFmApi,
    private val artistConverter: ArtistConverter
) : ArtistGateway {

    override suspend fun searchArtists(artist: String): List<Artist> {
        val response = api.searchArtists(artist)

        return response.results.matches.artist.map(artistConverter::fromResponseToModel)
    }
}