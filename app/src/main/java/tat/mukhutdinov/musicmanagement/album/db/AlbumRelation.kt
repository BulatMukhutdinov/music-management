package tat.mukhutdinov.musicmanagement.album.db

import androidx.room.Embedded
import androidx.room.Relation
import tat.mukhutdinov.musicmanagement.track.db.TrackEntity

class AlbumRelation {

    @Embedded
    lateinit var albumEntity: AlbumEntity

    @Relation(parentColumn = AlbumEntity.COLUMN_ID, entityColumn = TrackEntity.COLUMN_ALBUM_ID, entity = TrackEntity::class)
    lateinit var tracks: List<TrackEntity>
}