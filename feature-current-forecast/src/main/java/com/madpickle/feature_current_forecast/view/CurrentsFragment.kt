package com.madpickle.feature_current_forecast.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.madpickle.core_android.createViewModel
import com.madpickle.core_android.navigateTo
import com.madpickle.core_android.observe
import com.madpickle.core_android.screens.Screen
import com.madpickle.core_android.view.BaseFragment
import com.madpickle.core_android.view.ResultView
import com.madpickle.core_android.view.WrapperLinearLayoutManager
import com.madpickle.core_data.model.CurrentModel
import com.madpickle.feature_current_forecast.databinding.FragmentCurrentsBinding
import com.madpickle.feature_current_forecast.di.DaggerFeatureCurrentComponent
import com.madpickle.feature_current_forecast.di.FeatureCurrentComponentProvider
import com.madpickle.feature_current_forecast.state.CurrentsViewState
import com.madpickle.feature_current_forecast.viewmodel.CurrentsViewModel
import timber.log.Timber


class CurrentsFragment : BaseFragment<FragmentCurrentsBinding>() {
    private val viewModel by createViewModel<CurrentsViewModel>()
    private var currentsAdapter: CurrentsAdapter? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCurrentsBinding
        get() = FragmentCurrentsBinding::inflate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = (context.applicationContext as FeatureCurrentComponentProvider).getCurrentComponentDependencies()
        val currentsComponent = DaggerFeatureCurrentComponent.builder().currentsComponentDependencies(dependencies).build()
        currentsComponent.injectCurrentsFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getCurrents()
    }

    override fun start() {
        currentsAdapter = CurrentsAdapter(this::itemSelect, viewModel::updateCurrent, viewModel::deleteCurrent)
        initViews()
        observe(viewModel.state){
            when(it){
                CurrentsViewState.Failed -> setErrorView()
                CurrentsViewState.InProgress -> setProgressView()
                CurrentsViewState.Empty -> setEmptyResult()
                is CurrentsViewState.Response -> setCurrentsListView(it.currents)
                is CurrentsViewState.Updated -> updateItemByIndex(it.current, it.index)
                is CurrentsViewState.Deleted -> deleteItemByIndex(it.current, it.index)
            }
        }
    }

    private fun itemSelect(region: String) {
        navigateTo(Screen.ForecastDetail(region))
    }

    private fun deleteItemByIndex(current: CurrentModel, index: Int) {
        currentsAdapter?.deleteItem(current, index)
    }

    private fun updateItemByIndex(current: CurrentModel, index: Int) {
        if(isVisible){
            currentsAdapter?.updatedItem(current, index)
        }
    }

    private fun initViews() {
        binding.currents.layoutManager = WrapperLinearLayoutManager(context)
        binding.currents.adapter = currentsAdapter
        binding.addLocation.setOnClickListener {
            navigateTo(Screen.SelectRegion())
        }
        binding.currents.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy <= 0)
                    binding.addLocation.extend()
                else if (dy > 0)
                    binding.addLocation.shrink()
            }
        })
    }

    private fun setCurrentsListView(currents: List<CurrentModel>) {
        binding.progressLayout.isVisible = false
        binding.resultBanner.isVisible = false
        currentsAdapter?.initItems(currents)
    }

    private fun setEmptyResult(){
        binding.progressLayout.isVisible = false
        binding.resultBanner.isVisible = true
    }

    private fun setErrorView() {
        Timber.e(CurrentsFragment::class.java.simpleName)
        binding.progressLayout.isVisible = false
        binding.resultBanner.isVisible = true
        binding.resultBanner.initResultView(
            ResultView.PropertyView.TitleText(getString(com.madpickle.core_android.R.string.currents_error_title)),
            ResultView.PropertyView.Icon(com.madpickle.core_android.R.drawable.ic_error),
            ResultView.PropertyView.SubTitleText(getString(com.madpickle.core_android.R.string.currents_error_text))
        )
    }

    private fun setProgressView() {
        binding.progressLayout.isVisible = true
    }
}