package tat.mukhutdinov.musicmanagement.albumssaved.ui

import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.paging.PagedList
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseViewModel

interface AlbumsSavedViewModel : BaseViewModel<AlbumsSavedViewModelBinding> {

    val directions: LiveData<NavDirections>

    val onAlbumDelete: LiveData<String>

    val albums: LiveData<PagedList<Album>>

    fun onSwiped(album: Album?)
}
