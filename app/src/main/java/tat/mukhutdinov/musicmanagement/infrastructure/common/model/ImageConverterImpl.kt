package tat.mukhutdinov.musicmanagement.infrastructure.common.model

import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.boundary.ImageConverter
import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.ImageResponse

class ImageConverterImpl : ImageConverter {

    override fun fromResponseToModel(response: List<ImageResponse>): Image {
        val map = toMap(response)

        return Image(
            small = map["small"].orEmpty(),
            medium = map["medium"].orEmpty(),
            large = map["large"].orEmpty(),
            extraLarge = map["extraLarge"].orEmpty()
        )
    }

    private fun toMap(images: List<ImageResponse>) =
        mutableMapOf<String, String>().apply {
            images.forEach {
                this[it.size] = it.url
            }
        }
}