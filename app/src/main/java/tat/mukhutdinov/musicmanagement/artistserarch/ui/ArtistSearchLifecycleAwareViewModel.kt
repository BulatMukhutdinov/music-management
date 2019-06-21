package tat.mukhutdinov.musicmanagement.artistserarch.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.*
import tat.mukhutdinov.musicmanagement.artistserarch.model.Artist
import tat.mukhutdinov.musicmanagement.artistserarch.ui.boundary.ArtistSearchUseCase
import tat.mukhutdinov.musicmanagement.home.ui.HomeFragmentDirections
import tat.mukhutdinov.musicmanagement.infrastructure.util.data.DataStateLiveData
import tat.mukhutdinov.musicmanagement.infrastructure.util.data.SingleLiveData
import timber.log.Timber

class ArtistSearchLifecycleAwareViewModel(
    private val artistSearchUseCase: ArtistSearchUseCase
) : ViewModel(), ArtistSearchViewModel, ArtistSearchViewModelBinding {

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

    override fun onSearchClicked(query: String) {
        artists.onStart()

        viewModelScope.launch {
            // https://github.com/Kotlin/kotlinx.coroutines/issues/1035
            withContext(Dispatchers.IO + exceptionHandler) {
                searchJob?.cancelAndJoin()

                searchJob = launch {
                    val result = artistSearchUseCase.execute(query)

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