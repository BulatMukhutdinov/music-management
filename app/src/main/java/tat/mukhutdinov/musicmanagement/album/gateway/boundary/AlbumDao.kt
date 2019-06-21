package tat.mukhutdinov.musicmanagement.album.gateway.boundary

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import tat.mukhutdinov.musicmanagement.album.db.AlbumEntity
import tat.mukhutdinov.musicmanagement.album.db.AlbumRelation
import tat.mukhutdinov.musicmanagement.track.db.TrackEntity

@Dao
abstract class AlbumDao {

    fun insert(album: AlbumEntity, tracks: List<TrackEntity>) {
        insert(album)
        insertTracks(*tracks.toTypedArray())
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTracks(vararg tracks: TrackEntity)

    @Transaction
    @Query("SELECT * FROM ${AlbumEntity.TABLE_NAME} ORDER BY ${AlbumEntity.COLUMN_CREATED_AT} DESC")
    abstract fun getAll(): DataSource.Factory<Int, AlbumRelation>

    @Transaction
    @Query("SELECT * FROM ${AlbumEntity.TABLE_NAME} WHERE ${AlbumEntity.COLUMN_ID} = :id")
    abstract fun findById(id: String): AlbumRelation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg albums: AlbumEntity)

    @Update
    abstract fun update(vararg albums: AlbumEntity)

    @Delete
    abstract fun delete(vararg albums: AlbumEntity)

    @Query("DELETE FROM ${AlbumEntity.TABLE_NAME}")
    abstract fun clear()
}