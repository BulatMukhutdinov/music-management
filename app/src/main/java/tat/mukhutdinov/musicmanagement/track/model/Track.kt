package tat.mukhutdinov.musicmanagement.track.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class Track(val id: Long, val name: String, val rank: Int, val duration: Int) : Parcelable {

    @IgnoredOnParcel
    val durationFormatted = "${duration / 60}:${String.format("%02d", duration % 60)}"
}