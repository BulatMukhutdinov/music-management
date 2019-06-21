package tat.mukhutdinov.musicmanagement.infrastructure.common.gateway

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ImageResponse(@SerializedName("#text") val url: String, val size: String)