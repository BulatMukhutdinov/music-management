package tat.mukhutdinov.musicmanagement.albumssaved.ui.boundary

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import tat.mukhutdinov.musicmanagement.album.model.Album

interface GetAllAlbumsUseCase {

    fun execute(): LiveData<PagedList<Album>>
}