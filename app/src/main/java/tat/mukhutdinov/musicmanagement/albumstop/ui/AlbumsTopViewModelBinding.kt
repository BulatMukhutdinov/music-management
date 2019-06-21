package tat.mukhutdinov.musicmanagement.albumstop.ui

import androidx.lifecycle.LiveData
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseViewModelBinding

interface AlbumsTopViewModelBinding : BaseViewModelBinding {

    val isListEmpty: LiveData<Boolean>

    val name: String

    fun onAlbumClicked(album: Album)

    fun onSaveClicked(album: Album)

    fun onBackClicked()
}