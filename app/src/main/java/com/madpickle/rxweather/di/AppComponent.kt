package com.madpickle.rxweather.di

import android.app.Application
import com.madpickle.core_di.CoreModule
import com.madpickle.rxweather.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by David Madilyan on 25.08.2022.
 */
@Singleton
@Component(
    modules = [
        CoreModule::class
    ]
)
interface AppComponent {
    fun inject(application: Application)
}