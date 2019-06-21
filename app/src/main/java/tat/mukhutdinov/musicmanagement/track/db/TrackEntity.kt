package tat.mukhutdinov.musicmanagement.track.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import tat.mukhutdinov.musicmanagement.album.db.AlbumEntity
import tat.mukhutdinov.musicmanagement.track.db.TrackEntity.Companion.COLUMN_ALBUM_ID
import tat.mukhutdinov.musicmanagement.track.db.TrackEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = AlbumEntity::class,
        parentColumns = arrayOf(AlbumEntity.COLUMN_ID),
        childColumns = arrayOf(COLUMN_ALBUM_ID),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
class TrackEntity(
    @ColumnInfo(name = COLUMN_NAME)
    var name: String,
    @ColumnInfo(name = COLUMN_RANK)
    var rank: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var id: Long = 0

    @ColumnInfo(name = COLUMN_ALBUM_ID)
    var albumId: String = ""

    companion object {
        const val TABLE_NAME = "track"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_RANK = "rank"
        const val COLUMN_ALBUM_ID = "album_id"
    }
}