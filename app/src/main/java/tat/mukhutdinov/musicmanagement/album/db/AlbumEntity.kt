package tat.mukhutdinov.musicmanagement.album.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import tat.mukhutdinov.musicmanagement.album.db.AlbumEntity.Companion.TABLE_NAME
import tat.mukhutdinov.musicmanagement.infrastructure.common.model.Image

@Entity(tableName = TABLE_NAME)
class AlbumEntity(
    @ColumnInfo(name = COLUMN_TITLE)
    var title: String,
    @ColumnInfo(name = COLUMN_ARTIST)
    var artist: String,
    @ColumnInfo(name = COLUMN_PLAY_COUNT)
    var playCount: Long,
    @Embedded
    var image: Image
) {
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    var id: String = artist + title

    @ColumnInfo(name = COLUMN_CREATED_AT)
    var createdAt: Long = System.currentTimeMillis()

    companion object {
        const val TABLE_NAME = "album"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_ARTIST = "artist"
        const val COLUMN_PLAY_COUNT = "play_count"
        const val COLUMN_CREATED_AT = "created_at"
    }
}