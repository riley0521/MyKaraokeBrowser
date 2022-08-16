package com.rpfcoding.mykaraokebrowser.di

import android.content.Context
import androidx.room.Room
import com.rpfcoding.mykaraokebrowser.data.local.KaraokeDatabase
import com.rpfcoding.mykaraokebrowser.data.local.SongDao
import com.rpfcoding.mykaraokebrowser.data.local.SongService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): KaraokeDatabase = Room.databaseBuilder(
        context,
        KaraokeDatabase::class.java,
        "karaoke_database"
    ).createFromAsset("database/karaoke_database.db").build()

    @Provides
    @Singleton
    fun provideSongDao(
        db: KaraokeDatabase
    ): SongDao = db.songDao()

    @Provides
    @Singleton
    fun provideSongService(
        songDao: SongDao
    ): SongService = SongService(songDao)
}