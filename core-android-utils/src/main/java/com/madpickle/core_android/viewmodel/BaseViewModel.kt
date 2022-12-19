package com.madpickle.core_android.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Created by David Madilyan on 19.12.2022.
 */
abstract class BaseViewModel: ViewModel() {
    protected val disposeBag = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}