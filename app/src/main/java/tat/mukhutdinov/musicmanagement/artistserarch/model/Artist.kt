package tat.mukhutdinov.musicmanagement.artistserarch.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import tat.mukhutdinov.musicmanagement.infrastructure.common.model.Image

@Parcelize
@Keep
data class Artist(
    val id: String,
    val name: String,
    val listenersAmount: Int,
    val image: Image
) : Parcelable