package com.madpickle.rxweather.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.madpickle.core_data.repositories.*
import com.madpickle.feature_current_forecast.di.CurrentsComponentDependencies
import com.madpickle.feature_days_forecast.di.ForecastComponentDependencies
import com.madpickle.feature_places.di.PlacesComponentDependencies
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
        ViewModelModule::class
    ]
)
interface AppComponent: CurrentsComponentDependencies, ForecastComponentDependencies, PlacesComponentDependencies {

    override fun getLocationRepo(): ILocationRepository

    override fun getCurrentRepo(): ICurrentWeatherRepository

    override fun getForecastRepo(): IForecastRepository

    override fun getAstronomyRepo(): IAstronomyRepository

    fun getViewModelFactory(): ViewModelProvider.Factory

    fun inject(application: Application)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}