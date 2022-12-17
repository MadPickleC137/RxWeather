package com.madpickle.feature_days_forecast.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.madpickle.core_android.view.BaseFragment
import com.madpickle.feature_days_forecast.databinding.FragmentRootForecastBinding

/**
 * Created by David Madilyan on 18.12.2022.
 */
class ForecastFragment: BaseFragment<FragmentRootForecastBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRootForecastBinding
        get() = FragmentRootForecastBinding::inflate

    override fun start() {

    }
}