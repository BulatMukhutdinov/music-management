package tat.mukhutdinov.musicmanagement.albumstop.ui

import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseViewModel
import tat.mukhutdinov.musicmanagement.infrastructure.util.data.DataStateLiveData

interface AlbumsTopViewModel : BaseViewModel<AlbumsTopViewModelBinding> {

    val albums: DataStateLiveData<List<Album>>

    val navigateUp: LiveData<Unit>

    val directions: LiveData<NavDirections>
}