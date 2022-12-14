package com.madpickle.feature_current_forecast.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.madpickle.core_ext.BaseFragment
import com.madpickle.core_ext.createViewModel
import com.madpickle.feature_current_forecast.databinding.FragmentCurrentsBinding
import com.madpickle.feature_current_forecast.di.DaggerFeatureCurrentComponent
import com.madpickle.feature_current_forecast.di.FeatureCurrentComponentProvider
import com.madpickle.feature_current_forecast.viewmodel.CurrentsViewModel

class CurrentsFragment : BaseFragment<FragmentCurrentsBinding>() {
    private val viewModel by createViewModel<CurrentsViewModel>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCurrentsBinding
        get() = FragmentCurrentsBinding::inflate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = (context.applicationContext as FeatureCurrentComponentProvider).getCurrentComponentDependencies()
        val currentsComponent = DaggerFeatureCurrentComponent.builder().currentsComponentDependencies(dependencies).build()
        currentsComponent.injectCurrentsFragment(this)
    }

    override fun start() {

    }

}