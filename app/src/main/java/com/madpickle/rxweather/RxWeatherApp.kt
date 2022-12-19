package com.madpickle.rxweather

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.madpickle.core_android.di.ViewModelFactoryProvider
import com.madpickle.feature_current_forecast.di.CurrentsComponentDependencies
import com.madpickle.feature_current_forecast.di.FeatureCurrentComponentProvider
import com.madpickle.feature_days_forecast.di.FeatureForecastComponentProvider
import com.madpickle.feature_days_forecast.di.ForecastComponentDependencies
import com.madpickle.feature_places.di.FeaturePlacesComponentProvider
import com.madpickle.feature_places.di.PlacesComponentDependencies
import com.madpickle.rxweather.di.AppComponent
import com.madpickle.rxweather.di.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber


/**
 * Created by David Madilyan on 27.08.2022.
 */
class RxWeatherApp: Application(), ViewModelFactoryProvider, FeatureCurrentComponentProvider,
    FeatureForecastComponentProvider, FeaturePlacesComponentProvider {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        initRealm()
        initLogs()
    }

    private fun initLogs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name(Constants.DATA_BASE_NAME)
            .deleteRealmIfMigrationNeeded()
            .allowQueriesOnUiThread(false)
            .allowWritesOnUiThread(false)
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

    override fun getPlacesDependencies(): PlacesComponentDependencies {
        return  appComponent
    }
}