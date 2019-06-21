package tat.mukhutdinov.musicmanagement.infrastructure.common.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class Image(
    var small: String,
    var medium: String,
    var large: String,
    var extraLarge: String
) : Parcelable