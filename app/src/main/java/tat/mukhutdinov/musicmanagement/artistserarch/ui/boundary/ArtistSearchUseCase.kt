package tat.mukhutdinov.musicmanagement.artistserarch.ui.boundary

import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist

interface ArtistSearchUseCase {

    suspend fun execute(query: String): List<Artist>
}