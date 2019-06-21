package tat.mukhutdinov.musicmanagement.albumssaved.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumssaved.ui.AlbumsSavedViewModelBinding

class AlbumsSavedAdapter(
    private val albumsSavedViewModelBinding: AlbumsSavedViewModelBinding
) : PagedListAdapter<Album, AlbumSavedViewHolder>(AlbumDiffUtilItemCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumSavedViewHolder =
        AlbumSavedViewHolder.create(parent, albumsSavedViewModelBinding)

    override fun onBindViewHolder(holderSaved: AlbumSavedViewHolder, position: Int) {
        holderSaved.bindTo(getItem(position))
    }

    override fun onViewRecycled(holderSaved: AlbumSavedViewHolder) {
        super.onViewRecycled(holderSaved)
        holderSaved.onViewRecycled()
    }

    override fun getItemId(position: Int): Long =
        getItem(position)?.id.hashCode().toLong()
}