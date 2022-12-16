package com.madpickle.core_android.screens

/**
 * Created by David Madilyan on 15.12.2022.
 */
interface NavigationListener {
    fun onBack()
    fun navigateTo(screen: Screen)
    fun exit()
}