package com.madpickle.core_ext

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by David Madilyan on 14.12.2022.
 */
abstract class BaseFragment<VB: ViewBinding>: Fragment() {
    private var _binding: VB? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        start()
    }

    abstract fun start()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}