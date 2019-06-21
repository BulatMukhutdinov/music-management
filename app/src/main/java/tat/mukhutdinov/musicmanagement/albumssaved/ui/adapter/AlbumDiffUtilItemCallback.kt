package tat.mukhutdinov.musicmanagement.albumssaved.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import tat.mukhutdinov.musicmanagement.album.model.Album

class AlbumDiffUtilItemCallback : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
        oldItem == newItem
}