package com.rpfcoding.mykaraokebrowser.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.rpfcoding.mykaraokebrowser.data.local.dao.SongEntity

@Dao
interface SongDao {

    @Query("SELECT * FROM tbl_songs ORDER BY title")
    fun getSome(): PagingSource<Int, SongEntity>

    @Query("SELECT * FROM tbl_songs WHERE title LIKE '%' || :query || '%' OR singer LIKE '%' || :query || '%'")
    fun searchByNameOrSinger(query: String): PagingSource<Int, SongEntity>
}