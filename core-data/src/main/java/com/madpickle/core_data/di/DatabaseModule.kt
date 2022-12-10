package com.madpickle.core_data.di

import com.madpickle.core_data.Constants
import com.madpickle.core_data.realm_db.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton


/**
 * Created by David Madilyan on 25.08.2022.
 */
@Module(includes = [RepositoryModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRealm(realmConfiguration: RealmConfiguration): Realm {
        return Realm.getInstance(realmConfiguration)
    }


    @Provides
    @Singleton
    fun provideConfiguration(): RealmConfiguration{
        return RealmConfiguration.Builder().name(Constants.DATA_BASE_NAME)
            .deleteRealmIfMigrationNeeded()
            .build()
    }


    @Binds
    @Singleton
    fun provideCurrentWeatherDao(realm: Realm): CurrentWeatherDao {
        return CurrentWeatherDao(realm)
    }

    @Binds
    @Singleton
    fun provideForecastDao(realm: Realm): ForecastDao {
        return ForecastDao(realm)
    }

    @Binds
    @Singleton
    fun provideLocationDao(realm: Realm): LocationDao {
        return LocationDao(realm)
    }

    @Binds
    @Singleton
    fun provideAstronomyDao(realm: Realm): AstronomyDao {
        return AstronomyDao(realm)
    }
}