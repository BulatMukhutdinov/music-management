package tat.mukhutdinov.musicmanagement.albumstop.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import tat.mukhutdinov.musicmanagement.album.model.Album

class AlbumDiffUtilCallback(private val oldList: List<Album>, private val newList: List<Album>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}