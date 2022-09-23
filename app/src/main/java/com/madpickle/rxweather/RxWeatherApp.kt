package com.madpickle.rxweather

import android.app.Application
import com.madpickle.core_di.CoreModule
import com.madpickle.rxweather.di.AppComponent
import com.madpickle.rxweather.di.AppComponentProvider
import com.madpickle.rxweather.di.DaggerAppComponent

/**
 * Created by David Madilyan on 27.08.2022.
 */
class RxWeatherApp: Application(), AppComponentProvider {

    private val coreModule: CoreModule by lazy {
        CoreModule(this)
    }

    override fun onCreate() {
        super.onCreate()
        (this as AppComponentProvider)
            .getApplicationComponent()
            .inject(this)
    }

    override fun getApplicationComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .build()
    }
}