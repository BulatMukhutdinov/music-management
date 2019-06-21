package tat.mukhutdinov.musicmanagement.track.model

import tat.mukhutdinov.musicmanagement.album.gateway.TrackResponse
import tat.mukhutdinov.musicmanagement.track.db.TrackEntity
import tat.mukhutdinov.musicmanagement.track.gateway.boundary.TrackConverter

class TrackConverterImpl : TrackConverter {

    override fun fromResponseToModel(response: TrackResponse): Track =
        Track(
            id = 0,
            rank = response.rankResponse.rank.toInt(),
            name = response.name
        )

    override fun fromResponseToEntity(response: TrackResponse): TrackEntity =
        TrackEntity(
            name = response.name,
            rank = response.rankResponse.rank.toInt()
        )

    override fun fromEntityToModel(entity: TrackEntity): Track =
        Track(
            id = entity.id,
            rank = entity.rank,
            name = entity.name
        )

    override fun fromModelToEntity(model: Track): TrackEntity =
        TrackEntity(
            name = model.name,
            rank = model.rank
        ).apply { id = model.id }
}