package tat.mukhutdinov.musicmanagement.albumstop.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tat.mukhutdinov.musicmanagement.albumstop.interactor.GetTopAlbumsInteractor
import tat.mukhutdinov.musicmanagement.albumstop.interactor.SaveAlbumInteractor
import tat.mukhutdinov.musicmanagement.albumstop.ui.AlbumsTopFragmentArgs
import tat.mukhutdinov.musicmanagement.albumstop.ui.AlbumsTopLifecycleAwareViewModel
import tat.mukhutdinov.musicmanagement.albumstop.ui.boundary.GetTopAlbumsUseCase
import tat.mukhutdinov.musicmanagement.albumstop.ui.boundary.SaveAlbumUseCase

object AlbumsTopInjectionModule {

    val module = module {

        viewModel { (args: AlbumsTopFragmentArgs) ->
            AlbumsTopLifecycleAwareViewModel(args, get(), get())
        }

        factory<GetTopAlbumsUseCase> {
            GetTopAlbumsInteractor(get())
        }

        factory<SaveAlbumUseCase> {
            SaveAlbumInteractor(get())
        }
    }
}