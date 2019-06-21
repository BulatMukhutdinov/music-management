package tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.boundary

import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.ImageResponse
import tat.mukhutdinov.musicmanagement.infrastructure.common.model.Image

interface ImageConverter {

    fun fromResponseToModel(response: List<ImageResponse>): Image
}