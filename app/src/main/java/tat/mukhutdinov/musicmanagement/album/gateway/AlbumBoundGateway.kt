package tat.mukhutdinov.musicmanagement.album.gateway

import androidx.paging.Config
import androidx.paging.toLiveData
import tat.mukhutdinov.musicmanagement.album.gateway.boundary.AlbumConverter
import tat.mukhutdinov.musicmanagement.album.gateway.boundary.AlbumDao
import tat.mukhutdinov.musicmanagement.album.interactor.boundary.AlbumGateway
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.LastFmApi

class AlbumBoundGateway(
    private val albumDao: AlbumDao,
    private val lastFmApi: LastFmApi,
    private val converter: AlbumConverter
) : AlbumGateway {

    private val pagingConfig = Config(
        initialLoadSizeHint = 25,
        pageSize = 50,
        prefetchDistance = 100
    )

    override suspend fun delete(album: Album) {
        val relation = converter.fromModelToRelation(album)
        albumDao.delete(relation.albumEntity)
    }

    override suspend fun save(album: Album) {
        val response = lastFmApi.getAlbumInfo(artistName = album.artist, albumName = album.title)
        val relation = converter.fromResponseToRelation(response)
        albumDao.insert(relation.albumEntity, relation.tracks)
    }

    override suspend fun get(albumName: String, artistName: String): Album {
        val relation = albumDao.findById(artistName + albumName)

        return if (relation == null) {
            val response = lastFmApi.getAlbumInfo(artistName = artistName, albumName = albumName)
            converter.fromResponseToModel(response)
        } else {
            converter.fromRelationToModel(relation)
        }
    }

    override fun getAllLocal() =
        albumDao.getAll()
            .map(converter::fromRelationToModel)
            .map { it.copy(isSavedLocally = albumDao.findById(it.id) != null) }
            .toLiveData(pagingConfig)

    override suspend fun getTopOfArtist(artistName: String): List<Album> {
        val response = lastFmApi.getTopAlbums(artistName)

        return response.topAlbums.albums
            .map(converter::fromResponseToModel)
            .map { it.copy(isSavedLocally = albumDao.findById(it.id) != null) }
    }
}