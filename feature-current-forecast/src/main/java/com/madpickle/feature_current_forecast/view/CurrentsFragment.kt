package com.madpickle.feature_current_forecast.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.madpickle.core_data.model.CurrentModel
import com.madpickle.core_android.BaseFragment
import com.madpickle.core_android.createViewModel
import com.madpickle.core_android.observe
import com.madpickle.feature_current_forecast.databinding.FragmentCurrentsBinding
import com.madpickle.feature_current_forecast.di.DaggerFeatureCurrentComponent
import com.madpickle.feature_current_forecast.di.FeatureCurrentComponentProvider
import com.madpickle.feature_current_forecast.model.CurrentsViewState
import com.madpickle.feature_current_forecast.viewmodel.CurrentsViewModel
import timber.log.Timber

class CurrentsFragment : BaseFragment<FragmentCurrentsBinding>() {
    private val viewModel by createViewModel<CurrentsViewModel>()
    private val currentsAdapter = CurrentsAdapter()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCurrentsBinding
        get() = FragmentCurrentsBinding::inflate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = (context.applicationContext as FeatureCurrentComponentProvider).getCurrentComponentDependencies()
        val currentsComponent = DaggerFeatureCurrentComponent.builder().currentsComponentDependencies(dependencies).build()
        currentsComponent.injectCurrentsFragment(this)
    }

    override fun start() {
        binding.currents.layoutManager = LinearLayoutManager(context)
        binding.currents.adapter = currentsAdapter
        viewModel.getCurrents()
        observe(viewModel.state){
            when(it){
                CurrentsViewState.Failed -> setErrorView()
                CurrentsViewState.InProgress -> setProgressView()
                is CurrentsViewState.Response -> setCurrentsListView(it.currents)
                else -> {}
            }
        }
    }

    private fun setCurrentsListView(currents: List<CurrentModel>) {
        currentsAdapter.initItems(currents)
    }

    private fun setErrorView() {
        Timber.e(CurrentsFragment::class.java.simpleName)
    }

    private fun setProgressView() {
        binding.progressLayout.isVisible = true
    }

}