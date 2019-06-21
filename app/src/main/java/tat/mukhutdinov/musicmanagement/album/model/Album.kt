package tat.mukhutdinov.musicmanagement.album.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import tat.mukhutdinov.musicmanagement.infrastructure.common.model.Image
import tat.mukhutdinov.musicmanagement.track.model.Track

@Parcelize
@Keep
data class Album(
    val title: String,
    val artist: String,
    val image: Image,
    val playCount: Long,
    val tracks: List<Track>,
    val isSavedLocally: Boolean = false
) : Parcelable {

    @IgnoredOnParcel
    val id = artist + title
}