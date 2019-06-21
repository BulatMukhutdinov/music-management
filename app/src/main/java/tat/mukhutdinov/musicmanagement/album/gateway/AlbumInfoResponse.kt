package tat.mukhutdinov.musicmanagement.album.gateway

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.ImageResponse

@Keep
data class AlbumInfoResponse(@SerializedName("album") val album: AlbumResponse)

@Keep
data class AlbumResponse(
    val name: String,
    val artist: String,
    @SerializedName("image") val images: List<ImageResponse>,
    val listeners: Long,
    @SerializedName("playcount") val playCount: Long,
    val tracks: TracksResponse
)

@Keep
data class TracksResponse(@SerializedName("track") val tracks: List<TrackResponse>)

@Keep
data class TrackResponse(
    val name: String,
    val duration: Int,
    @SerializedName("@attr") val rankResponse: TrackRankResponse
)

@Keep
data class TrackRankResponse(val rank: String)