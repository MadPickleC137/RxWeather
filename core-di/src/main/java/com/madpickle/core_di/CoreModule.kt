package com.madpickle.core_di

import android.app.Application
import android.content.Context
import com.madpickle.core_data.di.DatabaseModule
import com.madpickle.core_ext.utils.StringsProvider
import com.madpickle.core_network.di.WeatherServiceModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by David Madilyan on 26.08.2022.
 */

@Module(includes = [DatabaseModule::class])
class CoreModule(private val application: Application) {
    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    fun stringsProvider() = StringsProvider(application)
}