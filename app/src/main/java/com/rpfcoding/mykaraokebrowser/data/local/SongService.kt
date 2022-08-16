package com.rpfcoding.mykaraokebrowser.data.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rpfcoding.mykaraokebrowser.data.local.dao.SongEntity
import kotlinx.coroutines.flow.Flow

class SongService(
    private val songDao: SongDao
) {

    fun getSome(): Flow<PagingData<SongEntity>> {
        val pagingSource = { songDao.getSome() }
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 30
            ),
            pagingSourceFactory = pagingSource
        ).flow
    }

    fun searchByNameOrSinger(query: String): Flow<PagingData<SongEntity>> {
        val pagingSource = { songDao.searchByNameOrSinger(query) }
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 30
            ),
            pagingSourceFactory = pagingSource
        ).flow
    }
}