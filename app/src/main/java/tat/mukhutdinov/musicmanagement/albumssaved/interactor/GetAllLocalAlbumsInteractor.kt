package tat.mukhutdinov.musicmanagement.albumssaved.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import tat.mukhutdinov.musicmanagement.album.interactor.boundary.AlbumGateway
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumssaved.ui.boundary.GetAllAlbumsUseCase

class GetAllLocalAlbumsInteractor(private val albumGateway: AlbumGateway) : GetAllAlbumsUseCase {

    override fun execute(): LiveData<PagedList<Album>> =
        albumGateway.getAllLocal()
}