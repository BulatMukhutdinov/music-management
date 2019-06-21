package tat.mukhutdinov.musicmanagement.album.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import tat.mukhutdinov.musicmanagement.R
import tat.mukhutdinov.musicmanagement.album.ui.AlbumViewModelBinding
import tat.mukhutdinov.musicmanagement.databinding.TrackItemBinding
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.adapter.BaseViewHolder
import tat.mukhutdinov.musicmanagement.track.model.Track

class TrackViewHolder(
    private val binding: TrackItemBinding,
    private val viewModelBinding: AlbumViewModelBinding
) : BaseViewHolder<Track>(binding.root) {

    override fun bindTo(item: Track?) {
        super.bindTo(item)

        binding.track = item
        binding.binding = viewModelBinding

        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup, viewModelBinding: AlbumViewModelBinding): TrackViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: TrackItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.track_item, parent, false)

            return TrackViewHolder(binding, viewModelBinding)
        }
    }
}