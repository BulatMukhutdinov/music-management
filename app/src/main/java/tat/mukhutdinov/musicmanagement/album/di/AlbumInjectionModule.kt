package tat.mukhutdinov.musicmanagement.album.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tat.mukhutdinov.musicmanagement.album.gateway.AlbumBoundGateway
import tat.mukhutdinov.musicmanagement.album.gateway.boundary.AlbumConverter
import tat.mukhutdinov.musicmanagement.album.interactor.GetAlbumInteractor
import tat.mukhutdinov.musicmanagement.album.interactor.boundary.AlbumGateway
import tat.mukhutdinov.musicmanagement.album.model.AlbumConverterImpl
import tat.mukhutdinov.musicmanagement.album.ui.AlbumFragmentArgs
import tat.mukhutdinov.musicmanagement.album.ui.AlbumLifecycleAwareViewModel
import tat.mukhutdinov.musicmanagement.album.ui.boundary.GetAlbumUseCase
import tat.mukhutdinov.musicmanagement.infrastructure.common.db.DataBase

object AlbumInjectionModule {

    val module = module {

        viewModel { (args: AlbumFragmentArgs) ->
            AlbumLifecycleAwareViewModel(args, get(), get())
        }

        factory<AlbumConverter> {
            AlbumConverterImpl(get(), get())
        }

        factory<GetAlbumUseCase> {
            GetAlbumInteractor(get())
        }

        factory {
            get<DataBase>().albumDao()
        }

        single<AlbumGateway> {
            AlbumBoundGateway(
                albumDao = get(),
                lastFmApi = get(),
                converter = get()
            )
        }
    }
}