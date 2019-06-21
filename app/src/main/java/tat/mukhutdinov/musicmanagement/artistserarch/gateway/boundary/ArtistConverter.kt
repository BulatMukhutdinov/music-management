package tat.mukhutdinov.musicmanagement.artistserarch.gateway.boundary

import tat.mukhutdinov.musicmanagement.artistserarch.gateway.ArtistResponse
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist

interface ArtistConverter {

    fun fromResponseToModel(response: ArtistResponse): Artist
}