package tat.mukhutdinov.musicmanagement.infrastructure.di

import tat.mukhutdinov.musicmanagement.album.di.AlbumInjectionModule
import tat.mukhutdinov.musicmanagement.albumssaved.di.AlbumsSavedInjectionModule
import tat.mukhutdinov.musicmanagement.albumstop.di.AlbumsTopInjectionModule
import tat.mukhutdinov.musicmanagement.artistserarch.di.ArtistSearchInjectionModule
import tat.mukhutdinov.musicmanagement.home.di.HomeInjectionModule
import tat.mukhutdinov.musicmanagement.infrastructure.common.di.CommonInjectionModule
import tat.mukhutdinov.musicmanagement.track.di.TrackInjectionModule

object InjectionModules {

    val modules = listOf(
        CommonInjectionModule.module,
        AlbumsSavedInjectionModule.module,
        HomeInjectionModule.module,
        ArtistSearchInjectionModule.module,
        TrackInjectionModule.module,
        AlbumsTopInjectionModule.module,
        AlbumInjectionModule.module
    )
}