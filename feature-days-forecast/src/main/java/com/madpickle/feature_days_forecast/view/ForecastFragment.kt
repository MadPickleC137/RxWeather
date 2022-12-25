package com.madpickle.feature_days_forecast.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.madpickle.core_android.createViewModel
import com.madpickle.core_android.screens.Screen
import com.madpickle.core_android.view.BaseFragment
import com.madpickle.feature_days_forecast.databinding.FragmentRootForecastBinding
import com.madpickle.feature_days_forecast.di.DaggerFeatureForecastComponent
import com.madpickle.feature_days_forecast.di.FeatureForecastComponentProvider
import com.madpickle.feature_days_forecast.viewmodel.ForecastViewModel

/**
 * Created by David Madilyan on 18.12.2022.
 */
class ForecastFragment: BaseFragment<FragmentRootForecastBinding>() {

    private var hoursAdapter: HoursAdapter? = null
    private var alertsAdapter: AlertsAdapter? = null
    private val viewModel by createViewModel<ForecastViewModel>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRootForecastBinding
        get() = FragmentRootForecastBinding::inflate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = (context.applicationContext as FeatureForecastComponentProvider).getForecastComponentDependencies()
        val currentsComponent = DaggerFeatureForecastComponent.builder().forecastComponentDependencies(dependencies).build()
        currentsComponent.injectRootFragment(this)
        viewModel.setArgs(arguments?.getString(Screen.ForecastDetail.ARGS) ?: "")
    }

    override fun start() {
        alertsAdapter = AlertsAdapter()
        hoursAdapter = HoursAdapter {
            viewModel.onHourItemSelected(it)
        }
        binding.alertsRecycler.layoutManager = object: LinearLayoutManager(context){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

    }
}