package tat.mukhutdinov.musicmanagement.albumstop.gateway

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.ImageResponse

@Keep
data class GetTopAlbumsResponse(@SerializedName("topalbums") val topAlbums: TopAlbums)

@Keep
data class TopAlbums(@SerializedName("album") val albums: List<AlbumResponse>)

@Keep
data class AlbumResponse(
    val name: String,
    @SerializedName("image") val images: List<ImageResponse>,
    @SerializedName("playcount") val playCount: Long,
    val artist: ArtistResponse
)

@Keep
data class ArtistResponse(
    val name: String
)