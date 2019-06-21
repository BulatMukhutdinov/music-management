package tat.mukhutdinov.musicmanagement.infrastructure.common.gateway

import retrofit2.http.GET
import retrofit2.http.Query
import tat.mukhutdinov.musicmanagement.album.gateway.AlbumInfoResponse
import tat.mukhutdinov.musicmanagement.albumstop.gateway.GetTopAlbumsResponse
import tat.mukhutdinov.musicmanagement.artistserarch.gateway.ArtistSearchResponse

interface LastFmApi {

    @GET("?method=artist.search")
    suspend fun searchArtists(@Query("artist") artist: String): ArtistSearchResponse

    @GET("?method=artist.gettopalbums")
    suspend fun getTopAlbums(@Query("artist") artistName: String): GetTopAlbumsResponse

    @GET("?method=album.getinfo")
    suspend fun getAlbumInfo(@Query("artist") artistName: String, @Query("album") albumName: String): AlbumInfoResponse
}