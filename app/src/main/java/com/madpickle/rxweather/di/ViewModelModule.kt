package com.madpickle.rxweather.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madpickle.core_android.viewmodel.ViewModelFactory
import com.madpickle.core_android.viewmodel.ViewModelKey
import com.madpickle.feature_current_forecast.viewmodel.CurrentsViewModel
import com.madpickle.feature_days_forecast.viewmodel.ForecastViewModel
import com.madpickle.feature_places.viewmodel.PlacesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by David Madilyan on 16.12.2022.
 */
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CurrentsViewModel::class)
    abstract fun bindCurrentsViewModel(viewModel: CurrentsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlacesViewModel::class)
    abstract fun bindPlacesViewModel(viewModel: PlacesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    abstract fun bindForecastViewModel(viewModel: ForecastViewModel): ViewModel
}