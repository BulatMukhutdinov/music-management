package tat.mukhutdinov.musicmanagement.artistserarch.model

import tat.mukhutdinov.musicmanagement.artistserarch.gateway.ArtistResponse
import tat.mukhutdinov.musicmanagement.artistserarch.gateway.boundary.ArtistConverter
import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.boundary.ImageConverter

class ArtistConverterImpl(private val imageConverter: ImageConverter) : ArtistConverter {

    override fun fromResponseToModel(response: ArtistResponse): Artist =
        Artist(
            id = response.id,
            name = response.name,
            image = imageConverter.fromResponseToModel(response.images),
            listenersAmount = response.listenersAmount
        )
}