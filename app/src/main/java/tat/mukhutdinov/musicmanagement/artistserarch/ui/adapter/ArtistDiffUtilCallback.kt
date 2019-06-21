package tat.mukhutdinov.musicmanagement.artistserarch.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist

class ArtistDiffUtilCallback(private val oldList: List<Artist>, private val newList: List<Artist>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}