package tat.mukhutdinov.musicmanagement.albumssaved.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tat.mukhutdinov.musicmanagement.albumssaved.interactor.DeleteAlbumInteractor
import tat.mukhutdinov.musicmanagement.albumssaved.interactor.GetAllLocalAlbumsInteractor
import tat.mukhutdinov.musicmanagement.albumssaved.ui.AlbumsSavedLifecycleAwareViewModel
import tat.mukhutdinov.musicmanagement.albumssaved.ui.boundary.DeleteAlbumUseCase
import tat.mukhutdinov.musicmanagement.albumssaved.ui.boundary.GetAllAlbumsUseCase

object AlbumsSavedInjectionModule {

    val module = module {

        viewModel {
            AlbumsSavedLifecycleAwareViewModel(get(), get())
        }

        factory<GetAllAlbumsUseCase> {
            GetAllLocalAlbumsInteractor(get())
        }

        factory<DeleteAlbumUseCase> {
            DeleteAlbumInteractor(get())
        }
    }
}