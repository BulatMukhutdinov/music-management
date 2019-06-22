package tat.mukhutdinov.musicmanagement.artistserarch.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist
import tat.mukhutdinov.musicmanagement.artistserarch.ui.boundary.ArtistSearchUseCase
import tat.mukhutdinov.musicmanagement.home.ui.HomeFragmentDirections
import tat.mukhutdinov.musicmanagement.infrastructure.util.data.DataStateLiveData
import tat.mukhutdinov.musicmanagement.infrastructure.util.data.SingleLiveData
import tat.mukhutdinov.musicmanagement.infrastructure.util.ui.Utils
import timber.log.Timber

class ArtistSearchLifecycleAwareViewModel(
    private val artistSearchUseCase: ArtistSearchUseCase
) : ViewModel(), ArtistSearchViewModel, ArtistSearchViewModelBinding {
    override val query = MutableLiveData<String>()

    override val isListEmpty = MutableLiveData<Boolean>()

    override val artists = DataStateLiveData.create<List<Artist>>()

    override val binding = this

    override val directions = SingleLiveData<NavDirections>()

    private var searchJob: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            artists.onError(throwable)
        }

        Timber.e(throwable)
    }

    override fun onSearchClicked(view: View) {
        Utils.hideKeyboard(view)

        artists.onStart()

        viewModelScope.launch {
            // https://github.com/Kotlin/kotlinx.coroutines/issues/1035
            withContext(Dispatchers.IO + exceptionHandler) {
                searchJob?.cancelAndJoin()

                searchJob = launch {
                    val result = artistSearchUseCase.execute(query.value.orEmpty())

                    withContext(Dispatchers.Main) {
                        artists.onNext(result)
                        isListEmpty.value = result.isEmpty()
                    }
                }
            }

        }
    }

    override fun onArtistClicked(artist: Artist) {
        directions.value = HomeFragmentDirections.toAlbumsTop(artist)
    }
}