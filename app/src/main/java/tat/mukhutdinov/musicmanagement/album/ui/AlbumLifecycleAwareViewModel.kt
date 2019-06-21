package tat.mukhutdinov.musicmanagement.album.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tat.mukhutdinov.musicmanagement.R
import tat.mukhutdinov.musicmanagement.album.ui.boundary.GetAlbumUseCase
import timber.log.Timber

class AlbumLifecycleAwareViewModel(
    private val args: AlbumFragmentArgs,
    getAlbumUseCase: GetAlbumUseCase,
    context: Context
) : ViewModel(), AlbumViewModelBinding, AlbumViewModel {

    override val tracks = MutableLiveData(args.album.tracks)

    override val album = MutableLiveData(args.album)

    override val navigateUp = MutableLiveData<Unit>()

    override val backgroundColor = MutableLiveData(ContextCompat.getColor(context, R.color.colorPrimaryDark))

    override val binding = this

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    private val target = object : Target {
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        }

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            Palette.from(bitmap).generate { palette ->
                val vibrantSwatch = palette?.vibrantSwatch
                if (vibrantSwatch != null) {
                    backgroundColor.postValue(vibrantSwatch.rgb)
                }
            }
        }
    }

    init {
        if (args.album.image.medium.isNotEmpty()) {
            Picasso.get()
                .load(args.album.image.medium)
                .into(target)
        }

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            if (!args.album.isSavedLocally) {
                val result = getAlbumUseCase.execute(albumName = args.album.title, artistName = args.album.artist)
                album.postValue(result)
                tracks.postValue(result.tracks)
            }
        }
    }

    override fun onBackClicked() {
        navigateUp.value = Unit
    }

    override fun onCleared() {
        super.onCleared()

        Picasso.get().cancelRequest(target)
    }
}