package com.madpickle.feature_places

import android.view.LayoutInflater
import android.view.ViewGroup
import com.madpickle.core_android.view.BaseFragment
import com.madpickle.feature_places.databinding.FragmentAddPlacesBinding

/**
 * Created by David Madilyan on 17.12.2022.
 */
class AddPlaceFragment: BaseFragment<FragmentAddPlacesBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddPlacesBinding
        get() = FragmentAddPlacesBinding::inflate

    override fun start() {

    }
}