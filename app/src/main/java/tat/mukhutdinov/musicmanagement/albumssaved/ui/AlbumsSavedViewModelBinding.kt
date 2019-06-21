package tat.mukhutdinov.musicmanagement.albumssaved.ui

import androidx.lifecycle.LiveData
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseViewModelBinding

interface AlbumsSavedViewModelBinding : BaseViewModelBinding {

    val isListEmpty: LiveData<Boolean>

    fun onAlbumClicked(album: Album)
}