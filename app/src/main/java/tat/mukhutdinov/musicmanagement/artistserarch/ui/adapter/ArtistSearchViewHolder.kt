package tat.mukhutdinov.musicmanagement.artistserarch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import tat.mukhutdinov.musicmanagement.R
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist
import tat.mukhutdinov.musicmanagement.artistserarch.ui.ArtistSearchViewModelBinding
import tat.mukhutdinov.musicmanagement.databinding.ArtistSearchItemBinding
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.adapter.BaseViewHolder

class ArtistSearchViewHolder(
    private val binding: ArtistSearchItemBinding,
    private val artistSearchViewModelBinding: ArtistSearchViewModelBinding
) : BaseViewHolder<Artist>(binding.root) {

    override fun bindTo(item: Artist?) {
        super.bindTo(item)

        binding.artist = item
        binding.binding = artistSearchViewModelBinding

        binding.icon.clipToOutline = true

        binding.executePendingBindings()
    }

    override fun onViewRecycled() {
        super.onViewRecycled()
        Picasso.get().cancelRequest(binding.icon)
    }

    companion object {
        fun create(parent: ViewGroup, artistSearchViewModelBinding: ArtistSearchViewModelBinding): ArtistSearchViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ArtistSearchItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.artist_search_item, parent, false)

            return ArtistSearchViewHolder(binding, artistSearchViewModelBinding)
        }
    }
}