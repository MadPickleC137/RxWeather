package com.madpickle.core_data.di

import android.content.Context
import androidx.room.Room
import com.madpickle.core_data.Constants.DATA_BASE_NAME
import com.madpickle.core_data.room_db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by David Madilyan on 25.08.2022.
 */
@Module(includes = [DataSourceModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class. java,
            DATA_BASE_NAME
        ).build()
    }

}