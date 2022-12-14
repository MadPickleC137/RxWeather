package com.madpickle.rxweather.di

import com.madpickle.core_data.di.RepositoryModule
import dagger.Module

/**
 * Created by David Madilyan on 26.08.2022.
 */

@Module(includes = [RepositoryModule::class])
class CoreModule