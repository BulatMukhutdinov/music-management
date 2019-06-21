package tat.mukhutdinov.musicmanagement.track.di

import org.koin.dsl.module
import tat.mukhutdinov.musicmanagement.track.gateway.boundary.TrackConverter
import tat.mukhutdinov.musicmanagement.track.model.TrackConverterImpl

object TrackInjectionModule {

    val module = module {

        factory<TrackConverter> {
            TrackConverterImpl()
        }
    }
}