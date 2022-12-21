package com.madpickle.rxweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.madpickle.core_android.screens.NavigationListener
import com.madpickle.core_android.screens.Screen
import com.madpickle.rxweather.databinding.ActivityMainBinding
import io.realm.Realm
import timber.log.Timber

class MainActivity : AppCompatActivity(), NavigationListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHost.navController
        setContentView(binding.root)
    }

    override fun onBack() {
        Timber.i("Навигация назад")
        navController.navigateUp()
    }

    override fun navigateTo(screen: Screen) {
        when(screen){
            is Screen.CurrentsList -> {}
            is Screen.ForecastDetail -> navController.navigate(R.id.action_currentsFragment_to_forecastFragment, screen.toBundle())
            is Screen.SelectRegion -> navController.navigate(R.id.action_currentsFragment_to_addPlaceFragment)
        }
        Timber.i("Экран: ${screen.title}")
    }

    override fun exit() {
        Timber.i("Выход")
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Realm.getDefaultInstance().close()
    }
}