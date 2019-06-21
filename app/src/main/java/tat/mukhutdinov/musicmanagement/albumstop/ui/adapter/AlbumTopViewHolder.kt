package tat.mukhutdinov.musicmanagement.albumstop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import tat.mukhutdinov.musicmanagement.R
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumstop.ui.AlbumsTopViewModelBinding
import tat.mukhutdinov.musicmanagement.databinding.AlbumsTopItemBinding
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.adapter.BaseViewHolder

class AlbumTopViewHolder(
    private val binding: AlbumsTopItemBinding,
    private val viewModelBinding: AlbumsTopViewModelBinding
) : BaseViewHolder<Album>(binding.root) {

    override fun bindTo(item: Album?) {
        super.bindTo(item)

        binding.album = item
        binding.binding = viewModelBinding

        binding.cover.clipToOutline = true

        if (item?.isSavedLocally == true) {
            binding.save.isEnabled = false
        }

        binding.executePendingBindings()
    }

    override fun onViewRecycled() {
        super.onViewRecycled()
        Picasso.get().cancelRequest(binding.cover)
    }

    companion object {
        fun create(parent: ViewGroup, viewModelBinding: AlbumsTopViewModelBinding): AlbumTopViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: AlbumsTopItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.albums_top_item, parent, false)

            return AlbumTopViewHolder(binding, viewModelBinding)
        }
    }
}