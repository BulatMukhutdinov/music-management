package tat.mukhutdinov.musicmanagement.artistserarch.ui

import androidx.lifecycle.LiveData
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseViewModelBinding

interface ArtistSearchViewModelBinding : BaseViewModelBinding {

    val isListEmpty: LiveData<Boolean>

    fun onArtistClicked(artist: Artist)
}