package tat.mukhutdinov.musicmanagement.artistserarch.gateway

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.ImageResponse

@Keep
data class ArtistSearchResponse(val results: Results)

@Keep
data class Results(@SerializedName("artistmatches") val matches: ArtistMatches)

@Keep
data class ArtistMatches(val artist: List<ArtistResponse>)

@Keep
data class ArtistResponse(@SerializedName("mbid") val id: String,
                          val name: String,
                          @SerializedName("listeners") val listenersAmount: Int,
                          @SerializedName("image")
                          val images: List<ImageResponse>)