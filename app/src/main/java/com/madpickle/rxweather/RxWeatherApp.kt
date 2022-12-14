package com.madpickle.rxweather

import android.app.Application
import com.madpickle.feature_current_forecast.di.CurrentsComponentDependencies
import com.madpickle.feature_current_forecast.di.FeatureCurrentComponentProvider
import com.madpickle.feature_days_forecast.di.FeatureForecastComponentProvider
import com.madpickle.feature_days_forecast.di.ForecastComponentDependencies
import com.madpickle.rxweather.di.AppComponent
import com.madpickle.rxweather.di.AppComponentProvider
import com.madpickle.rxweather.di.DaggerAppComponent

/**
 * Created by David Madilyan on 27.08.2022.
 */
class RxWeatherApp: Application(), AppComponentProvider, FeatureCurrentComponentProvider, FeatureForecastComponentProvider {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun getApplicationComponent(): AppComponent {
        return appComponent
    }

    override fun getCurrentComponentDependencies(): CurrentsComponentDependencies {
        return appComponent
    }

    override fun getForecastComponentDependencies(): ForecastComponentDependencies {
        return appComponent
    }
}