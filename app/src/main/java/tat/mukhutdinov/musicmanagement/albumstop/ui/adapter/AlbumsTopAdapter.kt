package tat.mukhutdinov.musicmanagement.albumstop.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumstop.ui.AlbumsTopViewModelBinding

class AlbumsTopAdapter(
    private val viewModelBinding: AlbumsTopViewModelBinding
) : RecyclerView.Adapter<AlbumTopViewHolder>() {

    init {
        setHasStableIds(true)
    }

    val albums: MutableList<Album> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumTopViewHolder =
        AlbumTopViewHolder.create(parent, viewModelBinding)

    override fun getItemCount(): Int =
        albums.size

    override fun onBindViewHolder(holder: AlbumTopViewHolder, position: Int) {
        holder.bindTo(albums[position])
    }

    override fun getItemId(position: Int): Long =
        albums[position].id.hashCode().toLong()
}