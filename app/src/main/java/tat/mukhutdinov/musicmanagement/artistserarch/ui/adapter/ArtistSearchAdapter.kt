package tat.mukhutdinov.musicmanagement.artistserarch.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist
import tat.mukhutdinov.musicmanagement.artistserarch.ui.ArtistSearchViewModelBinding

class ArtistSearchAdapter(
    private val artistSearchViewModelBinding: ArtistSearchViewModelBinding
) : RecyclerView.Adapter<ArtistSearchViewHolder>() {

    val artists: MutableList<Artist> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistSearchViewHolder =
        ArtistSearchViewHolder.create(parent, artistSearchViewModelBinding)

    override fun getItemCount(): Int =
        artists.size

    override fun onBindViewHolder(holder: ArtistSearchViewHolder, position: Int) {
        holder.bindTo(artists[position])
    }
}