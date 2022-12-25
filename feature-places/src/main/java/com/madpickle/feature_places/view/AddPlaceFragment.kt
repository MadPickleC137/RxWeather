package com.madpickle.feature_places.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.madpickle.core_android.*
import com.madpickle.core_android.view.BaseFragment
import com.madpickle.core_android.view.ResultView
import com.madpickle.core_data.model.LocationModel
import com.madpickle.feature_places.databinding.FragmentAddPlacesBinding
import com.madpickle.feature_places.di.DaggerFeaturePlacesComponent
import com.madpickle.feature_places.di.FeaturePlacesComponentProvider
import com.madpickle.feature_places.state.PlacesViewState
import com.madpickle.feature_places.viewmodel.PlacesViewModel
import java.util.concurrent.TimeUnit

/**
 * Created by David Madilyan on 17.12.2022.
 */
class AddPlaceFragment: BaseFragment<FragmentAddPlacesBinding>() {
    private val viewModel by createViewModel<PlacesViewModel>()
    private val adapter = ListPlaceAdapter()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddPlacesBinding
        get() = FragmentAddPlacesBinding::inflate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = (context.applicationContext as FeaturePlacesComponentProvider).getPlacesDependencies()
        val component = DaggerFeaturePlacesComponent.builder().placesComponentDependencies(dependencies).build()
        component.injectAddPlaceFragment(this)
    }


    override fun start() {
        setAdjustResizeMode()
        initViews()
        observe(viewModel.state){
            when(it){
                PlacesViewState.EmptySearch -> setEmptySearch()
                PlacesViewState.Init -> setInitView()
                PlacesViewState.Loading -> setLoadingView()
                is PlacesViewState.Places -> setPlaces(it.places)
                PlacesViewState.SelectSuccess -> setSuccessSelectPlace()
            }
        }
        observe(viewModel.hideKeyboard){
            context?.hideKeyboard(binding.searchText)
        }
    }

    private fun setSuccessSelectPlace() {
        onBack()
    }

    private fun initViews() {
        adapter.onItemClick = { viewModel.onItemSelected(it) }
        binding.listPlaces.layoutManager = LinearLayoutManager(context)
        binding.listPlaces.adapter = adapter
        binding.toolbar.setNavigationOnClickListener {
            onBack()
        }
        binding.searchText.textChanges()
            .debounce(642, TimeUnit.MILLISECONDS).subscribe { text ->
                if(!text.isNullOrBlank()){
                    viewModel.onSearchTextChanged(text)
                }
            }
    }

    private fun setPlaces(places: List<LocationModel>) {
        binding.progressLayout.isVisible = false
        binding.resultBanner.isVisible = false
        adapter.initItems(places)
    }

    private fun setLoadingView() {
        binding.progressLayout.isVisible = true
        binding.resultBanner.isVisible = false
    }

    private fun setInitView() {
        binding.progressLayout.isVisible = false
        binding.resultBanner.isVisible = true
        adapter.clearAll()
        binding.resultBanner.initResultView(
            ResultView.PropertyView.Icon(R.drawable.ic_find),
            ResultView.PropertyView.TitleText(getString(R.string.places_init_banner_title)),
            ResultView.PropertyView.SubTitleText(getString(R.string.places_init_banner_text))
        )
    }

    private fun setEmptySearch() {
        binding.progressLayout.isVisible = false
        binding.resultBanner.isVisible = true
        adapter.clearAll()
        binding.resultBanner.initResultView(
            ResultView.PropertyView.Icon(R.drawable.ic_find),
            ResultView.PropertyView.TitleText(getString(R.string.places_result_title)),
            ResultView.PropertyView.SubTitleText(getString(R.string.places_result_text))
        )
    }

    override fun onStop() {
        super.onStop()
        setAdjustPanMode()
    }
}