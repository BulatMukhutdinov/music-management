package tat.mukhutdinov.musicmanagement.infrastructure.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tat.mukhutdinov.musicmanagement.album.db.AlbumEntity
import tat.mukhutdinov.musicmanagement.album.gateway.boundary.AlbumDao
import tat.mukhutdinov.musicmanagement.track.db.TrackEntity
import tat.mukhutdinov.musicmanagement.track.gateway.boundary.TrackDao

@Database(
    entities = [
        AlbumEntity::class,
        TrackEntity::class
    ],
    version = 12)
abstract class DataBase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    abstract fun trackDao(): TrackDao
}