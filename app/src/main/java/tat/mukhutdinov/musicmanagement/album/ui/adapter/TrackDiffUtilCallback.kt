package tat.mukhutdinov.musicmanagement.album.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import tat.mukhutdinov.musicmanagement.track.model.Track

class TrackDiffUtilCallback(private val oldList: List<Track>, private val newList: List<Track>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}