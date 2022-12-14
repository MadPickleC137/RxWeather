package com.madpickle.core_android

import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madpickle.core_android.di.ViewModelFactoryProvider
import com.madpickle.core_android.screens.NavigationListener
import com.madpickle.core_android.screens.Screen
import com.madpickle.core_android.utils.AlertMessageEvent
import com.madpickle.core_android.utils.Event
import com.madpickle.core_android.utils.EventObserver

/**
 * Created by David Madilyan on 25.08.2022.
 */
inline fun <reified T : ViewModel> Fragment.createViewModel() = viewModels<T>{
    (requireActivity().applicationContext as ViewModelFactoryProvider).getViewModelFactory()
}

fun <T> Fragment.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner) {
        if(it != null){
            observer(it)
        }
    }
}

fun <T> Fragment.observeOnEvent(liveData: LiveData<Event<T>>, observer: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, EventObserver { observer(it) })
}

fun Fragment.navigateTo(screen: Screen){
    (activity as? NavigationListener)?.navigateTo(screen)
}

fun Fragment.onBack(){
    (activity as? NavigationListener)?.onBack()
}

fun Fragment.setAdjustResizeMode(){
    this.activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}

fun Fragment.setAdjustPanMode(){
    this.activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
}

fun Fragment.showAlertDialog(event: AlertMessageEvent) = context?.run {
    AlertDialog.Builder(this).apply {
        if(event.title.isNotBlank()) setTitle(event.title)
        setMessage(event.message)
        setPositiveButton("Ok") { _, _ ->
            event.action.invoke()
        }
        setOnDismissListener {
            event.dismiss.invoke()
        }
    }.show()
}