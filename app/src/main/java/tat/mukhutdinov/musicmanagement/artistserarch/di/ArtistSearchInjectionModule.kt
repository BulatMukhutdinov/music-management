package tat.mukhutdinov.musicmanagement.artistserarch.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tat.mukhutdinov.musicmanagement.artistserarch.gateway.ArtistRemoteGateway
import tat.mukhutdinov.musicmanagement.artistserarch.gateway.boundary.ArtistConverter
import tat.mukhutdinov.musicmanagement.artistserarch.interactor.ArtistSearchInteractor
import tat.mukhutdinov.musicmanagement.artistserarch.interactor.boundary.ArtistGateway
import tat.mukhutdinov.musicmanagement.artistserarch.model.ArtistConverterImpl
import tat.mukhutdinov.musicmanagement.artistserarch.ui.ArtistSearchLifecycleAwareViewModel
import tat.mukhutdinov.musicmanagement.artistserarch.ui.boundary.ArtistSearchUseCase

object ArtistSearchInjectionModule {

    val module = module {

        viewModel {
            ArtistSearchLifecycleAwareViewModel(get())
        }

        factory<ArtistSearchUseCase> {
            ArtistSearchInteractor(get())
        }

        factory<ArtistConverter> {
            ArtistConverterImpl(get())
        }

        single<ArtistGateway> {
            ArtistRemoteGateway(get(), get())
        }
    }
}