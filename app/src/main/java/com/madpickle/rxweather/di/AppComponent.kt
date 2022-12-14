package com.madpickle.rxweather.di

import android.app.Application
import android.content.Context
import com.madpickle.core_data.source.CurrentWeatherRepository
import com.madpickle.feature_current_forecast.di.CurrentsComponentDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by David Madilyan on 25.08.2022.
 */
@Singleton
@Component(
    modules = [
        CoreModule::class,
    ]
)
interface AppComponent: CurrentsComponentDependencies {

    override fun getRepo(): CurrentWeatherRepository

    fun inject(application: Application)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}