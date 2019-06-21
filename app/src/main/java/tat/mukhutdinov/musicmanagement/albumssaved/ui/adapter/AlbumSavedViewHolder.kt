package tat.mukhutdinov.musicmanagement.albumssaved.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import tat.mukhutdinov.musicmanagement.R
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumssaved.ui.AlbumsSavedViewModelBinding
import tat.mukhutdinov.musicmanagement.databinding.AlbumsSavedItemBinding
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.adapter.BaseViewHolder

class AlbumSavedViewHolder(
    private val binding: AlbumsSavedItemBinding,
    private val albumsSavedViewModelBinding: AlbumsSavedViewModelBinding
) : BaseViewHolder<Album>(binding.root) {

    override fun bindTo(item: Album?) {
        super.bindTo(item)

        binding.album = item
        binding.binding = albumsSavedViewModelBinding

        binding.cover.clipToOutline = true

        binding.executePendingBindings()
    }

    override fun onViewRecycled() {
        super.onViewRecycled()
        Picasso.get().cancelRequest(binding.cover)
    }

    companion object {
        fun create(parent: ViewGroup, albumsSavedViewModelBinding: AlbumsSavedViewModelBinding): AlbumSavedViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: AlbumsSavedItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.albums_saved_item, parent, false)

            return AlbumSavedViewHolder(binding, albumsSavedViewModelBinding)
        }
    }
}