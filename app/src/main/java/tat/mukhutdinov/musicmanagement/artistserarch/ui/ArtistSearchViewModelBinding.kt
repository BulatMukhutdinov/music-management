package tat.mukhutdinov.musicmanagement.artistserarch.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseViewModelBinding

interface ArtistSearchViewModelBinding : BaseViewModelBinding {

    val query: MutableLiveData<String>

    val isListEmpty: LiveData<Boolean>

    fun onSearchClicked(view: View)

    fun onArtistClicked(artist: Artist)
}