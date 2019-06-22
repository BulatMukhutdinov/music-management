package tat.mukhutdinov.musicmanagement.artistserarch.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseViewModel
import tat.mukhutdinov.musicmanagement.infrastructure.util.data.DataStateLiveData

interface ArtistSearchViewModel : BaseViewModel<ArtistSearchViewModelBinding> {

    val artists: DataStateLiveData<List<Artist>>

    val directions: LiveData<NavDirections>

    fun onSearchClicked(view: View)
}