package tat.mukhutdinov.musicmanagement.track.gateway.boundary

import tat.mukhutdinov.musicmanagement.album.gateway.TrackResponse
import tat.mukhutdinov.musicmanagement.track.db.TrackEntity
import tat.mukhutdinov.musicmanagement.track.model.Track

interface TrackConverter {

    fun fromEntityToModel(entity: TrackEntity): Track

    fun fromModelToEntity(model: Track): TrackEntity

    fun fromResponseToEntity(response: TrackResponse): TrackEntity

    fun fromResponseToModel(response: TrackResponse): Track
}