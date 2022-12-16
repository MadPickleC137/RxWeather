package com.madpickle.rxweather

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.madpickle.feature_current_forecast.di.CurrentsComponentDependencies
import com.madpickle.feature_current_forecast.di.FeatureCurrentComponentProvider
import com.madpickle.feature_days_forecast.di.FeatureForecastComponentProvider
import com.madpickle.feature_days_forecast.di.ForecastComponentDependencies
import com.madpickle.rxweather.di.AppComponent
import com.madpickle.core_android.di.ViewModelFactoryProvider
import com.madpickle.rxweather.di.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by David Madilyan on 27.08.2022.
 */
class RxWeatherApp: Application(), ViewModelFactoryProvider, FeatureCurrentComponentProvider, FeatureForecastComponentProvider {
    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name(Constants.DATA_BASE_NAME)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return appComponent.getViewModelFactory()
    }

    override fun getCurrentComponentDependencies(): CurrentsComponentDependencies {
        return appComponent
    }

    override fun getForecastComponentDependencies(): ForecastComponentDependencies {
        return appComponent
    }
}