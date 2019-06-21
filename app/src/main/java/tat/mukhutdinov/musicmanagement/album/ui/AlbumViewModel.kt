package tat.mukhutdinov.musicmanagement.album.ui

import androidx.lifecycle.LiveData
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseViewModel
import tat.mukhutdinov.musicmanagement.track.model.Track

interface AlbumViewModel : BaseViewModel<AlbumViewModelBinding> {

    val tracks: LiveData<List<Track>>

    val navigateUp: LiveData<Unit>
}