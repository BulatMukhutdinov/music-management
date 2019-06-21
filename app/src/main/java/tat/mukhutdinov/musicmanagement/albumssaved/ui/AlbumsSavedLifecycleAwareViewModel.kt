package tat.mukhutdinov.musicmanagement.albumssaved.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumssaved.ui.boundary.DeleteAlbumUseCase
import tat.mukhutdinov.musicmanagement.albumssaved.ui.boundary.GetAllAlbumsUseCase
import tat.mukhutdinov.musicmanagement.home.ui.HomeFragmentDirections
import tat.mukhutdinov.musicmanagement.infrastructure.util.data.SingleLiveData
import timber.log.Timber

class AlbumsSavedLifecycleAwareViewModel(
    getAllAlbumsUseCase: GetAllAlbumsUseCase,
    private val deleteAlbumUseCase: DeleteAlbumUseCase
) : ViewModel(), AlbumsSavedViewModelBinding, AlbumsSavedViewModel {

    override val onAlbumDelete = SingleLiveData<String>()

    override val albums: LiveData<PagedList<Album>> = getAllAlbumsUseCase.execute()

    override val isListEmpty = Transformations.map(albums) { it.isEmpty() }

    override val directions = SingleLiveData<NavDirections>()

    override val binding = this

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    override fun onAlbumClicked(album: Album) {
        directions.value = HomeFragmentDirections.toAlbum(album)
    }

    override fun onSwiped(album: Album?) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            if (album != null) {
                deleteAlbumUseCase.execute(album)
                onAlbumDelete.postValue(album.title)
            }
        }
    }
}