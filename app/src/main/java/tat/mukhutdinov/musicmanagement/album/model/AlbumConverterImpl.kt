package tat.mukhutdinov.musicmanagement.album.model

import tat.mukhutdinov.musicmanagement.album.db.AlbumEntity
import tat.mukhutdinov.musicmanagement.album.db.AlbumRelation
import tat.mukhutdinov.musicmanagement.album.gateway.AlbumInfoResponse
import tat.mukhutdinov.musicmanagement.album.gateway.boundary.AlbumConverter
import tat.mukhutdinov.musicmanagement.albumstop.gateway.AlbumResponse
import tat.mukhutdinov.musicmanagement.infrastructure.common.gateway.boundary.ImageConverter
import tat.mukhutdinov.musicmanagement.track.gateway.boundary.TrackConverter

class AlbumConverterImpl(
    private val trackConverter: TrackConverter,
    private val imageConverter: ImageConverter
) : AlbumConverter {

    override fun fromResponseToModel(response: AlbumInfoResponse): Album =
        Album(
            title = response.album.name,
            artist = response.album.artist,
            image = imageConverter.fromResponseToModel(response.album.images),
            playCount = response.album.playCount,
            tracks = response.album.tracks.tracks.map { trackConverter.fromResponseToModel(it) }
        )

    override fun fromModelToRelation(model: Album): AlbumRelation =
        AlbumRelation().apply {
            albumEntity = AlbumEntity(
                title = model.title,
                playCount = model.playCount,
                artist = model.artist,
                image = model.image
            )

            tracks = model.tracks
                .map(trackConverter::fromModelToEntity)
                .apply {
                    forEach { it.albumId = albumEntity.id }
                }
        }

    override fun fromRelationToModel(relation: AlbumRelation): Album =
        Album(
            title = relation.albumEntity.title,
            artist = relation.albumEntity.artist,
            image = relation.albumEntity.image,
            playCount = relation.albumEntity.playCount,
            tracks = relation.tracks.map { trackConverter.fromEntityToModel(it) }
        )

    override fun fromResponseToModel(response: AlbumResponse): Album =
        Album(
            title = response.name,
            artist = response.artist.name,
            image = imageConverter.fromResponseToModel(response.images),
            playCount = response.playCount,
            tracks = emptyList()
        )

    override fun fromResponseToRelation(response: AlbumInfoResponse): AlbumRelation =
        AlbumRelation().apply {
            albumEntity = AlbumEntity(
                title = response.album.name,
                image = imageConverter.fromResponseToModel(response.album.images),
                artist = response.album.artist,
                playCount = response.album.playCount
            )

            tracks = response.album.tracks.tracks
                .map(trackConverter::fromResponseToEntity)
                .apply {
                    forEach { it.albumId = albumEntity.id }
                }
        }
}