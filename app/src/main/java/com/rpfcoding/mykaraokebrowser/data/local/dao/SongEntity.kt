package com.rpfcoding.mykaraokebrowser.data.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_songs")
data class SongEntity(
    val title: String,
    val code: Int,
    val singer: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
