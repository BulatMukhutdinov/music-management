package tat.mukhutdinov.musicmanagement.albumstop.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumstop.ui.boundary.GetTopAlbumsUseCase
import tat.mukhutdinov.musicmanagement.albumstop.ui.boundary.SaveAlbumUseCase
import tat.mukhutdinov.musicmanagement.infrastructure.util.data.DataStateLiveData
import tat.mukhutdinov.musicmanagement.infrastructure.util.data.SingleLiveData
import timber.log.Timber

class AlbumsTopLifecycleAwareViewModel(
    private val args: AlbumsTopFragmentArgs,
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val saveAlbumUseCase: SaveAlbumUseCase
) : ViewModel(), AlbumsTopViewModel, AlbumsTopViewModelBinding {

    override val navigateUp = MutableLiveData<Unit>()

    override val binding = this

    override val name = args.artist.name

    override val isListEmpty = MutableLiveData<Boolean>()

    override val directions = SingleLiveData<NavDirections>()

    override val albums = DataStateLiveData.create<List<Album>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            albums.onError(throwable)
        }

        Timber.e(throwable)
    }

    init {
        updateAlbums()
    }

    private fun updateAlbums() {
        viewModelScope.launch(exceptionHandler) {
            albums.onStart()

            withContext(Dispatchers.IO) {
                val result = getTopAlbumsUseCase.execute(args.artist.name)

                withContext(Dispatchers.Main) {
                    albums.onNext(result)
                    isListEmpty.value = result.isEmpty()
                }
            }
        }
    }

    override fun onBackClicked() {
        navigateUp.value = Unit
    }

    override fun onAlbumClicked(album: Album) {
        directions.value = AlbumsTopFragmentDirections.toAlbum(album)
    }

    override fun onSaveClicked(album: Album) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            saveAlbumUseCase.execute(album)

            updateAlbums()
        }
    }
}