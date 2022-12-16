package com.madpickle.rxweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import com.madpickle.core_android.screens.NavigationListener
import com.madpickle.core_android.screens.Screen
import com.madpickle.rxweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBack() {
        findNavController(R.navigation.nav_graph).navigateUp()
    }

    override fun navigateTo(screen: Screen) {
        val navController = findNavController(R.navigation.nav_graph)
        when(screen){
            is Screen.CurrentsList -> TODO()
            is Screen.ForecastDetail -> TODO()
            is Screen.SelectRegion -> TODO()
        }
    }

    override fun exit() {
        finish()
    }
}