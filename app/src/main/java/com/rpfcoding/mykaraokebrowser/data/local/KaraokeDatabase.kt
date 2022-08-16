package com.rpfcoding.mykaraokebrowser.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rpfcoding.mykaraokebrowser.data.local.dao.SongEntity

@Database(
    entities = [SongEntity::class],
    version = 1,
    exportSchema = true
)
abstract class KaraokeDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao
}