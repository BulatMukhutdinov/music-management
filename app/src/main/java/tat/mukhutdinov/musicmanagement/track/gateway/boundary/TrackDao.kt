package tat.mukhutdinov.musicmanagement.track.gateway.boundary

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import tat.mukhutdinov.musicmanagement.track.db.TrackEntity

@Dao
interface TrackDao {

    @Query("SELECT * FROM ${TrackEntity.TABLE_NAME} WHERE ${TrackEntity.COLUMN_ID} = :id")
    fun findById(id: Long): LiveData<TrackEntity>

    @Update
    fun update(vararg tracks: TrackEntity)

    @Delete
    fun delete(vararg tracks: TrackEntity)

    @Query("DELETE FROM ${TrackEntity.TABLE_NAME}")
    fun clear()
}