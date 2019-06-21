package tat.mukhutdinov.musicmanagement.album.ui

import androidx.lifecycle.LiveData
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseViewModelBinding

interface AlbumViewModelBinding : BaseViewModelBinding {

    val backgroundColor: LiveData<Int>

    val album: LiveData<Album>

    fun onBackClicked()
}