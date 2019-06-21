package tat.mukhutdinov.musicmanagement.album.gateway.boundary

import tat.mukhutdinov.musicmanagement.album.db.AlbumRelation
import tat.mukhutdinov.musicmanagement.album.gateway.AlbumInfoResponse
import tat.mukhutdinov.musicmanagement.album.model.Album
import tat.mukhutdinov.musicmanagement.albumstop.gateway.AlbumResponse

interface AlbumConverter {

    fun fromModelToRelation(model: Album): AlbumRelation

    fun fromRelationToModel(relation: AlbumRelation): Album

    fun fromResponseToModel(response: AlbumResponse): Album

    fun fromResponseToModel(response: AlbumInfoResponse): Album

    fun fromResponseToRelation(response: AlbumInfoResponse): AlbumRelation
}