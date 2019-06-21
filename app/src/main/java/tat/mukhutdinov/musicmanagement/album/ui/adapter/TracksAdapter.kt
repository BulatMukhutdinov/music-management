package tat.mukhutdinov.musicmanagement.album.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tat.mukhutdinov.musicmanagement.album.ui.AlbumViewModelBinding
import tat.mukhutdinov.musicmanagement.track.model.Track

class TracksAdapter(
    private val viewModelBinding: AlbumViewModelBinding
) : RecyclerView.Adapter<TrackViewHolder>() {

    init {
        setHasStableIds(true)
    }

    val tracks: MutableList<Track> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder =
        TrackViewHolder.create(parent, viewModelBinding)

    override fun getItemCount(): Int =
        tracks.size

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bindTo(tracks[position])
    }

    override fun getItemId(position: Int): Long =
        tracks[position].id.hashCode().toLong()
}