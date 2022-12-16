package com.madpickle.core_android.di

import androidx.lifecycle.ViewModelProvider

/**
 * Created by David Madilyan on 25.08.2022.
 */
interface ViewModelFactoryProvider {
    fun getViewModelFactory(): ViewModelProvider.Factory
}