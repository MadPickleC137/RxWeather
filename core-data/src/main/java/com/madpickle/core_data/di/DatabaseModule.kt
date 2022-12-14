package com.madpickle.core_data.di

import com.madpickle.core_data.Constants
import com.madpickle.core_data.realm_db.*
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton


/**
 * Created by David Madilyan on 25.08.2022.
 */
@Module
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


    @Provides
    @Singleton
    fun provideCurrentWeatherDao(realm: Realm): CurrentWeatherDao {
        return CurrentWeatherDao(realm)
    }

    @Provides
    @Singleton
    fun provideForecastDao(realm: Realm): ForecastDao {
        return ForecastDao(realm)
    }

    @Provides
    @Singleton
    fun provideLocationDao(realm: Realm): LocationDao {
        return LocationDao(realm)
    }

    @Provides
    @Singleton
    fun provideAstronomyDao(realm: Realm): AstronomyDao {
        return AstronomyDao(realm)
    }
}