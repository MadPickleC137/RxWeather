package com.madpickle.core_android

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madpickle.core_android.di.ViewModelFactoryProvider
import com.madpickle.core_android.utils.AlertMessageEvent
import com.madpickle.core_android.utils.Event
import com.madpickle.core_android.utils.EventObserver

/**
 * Created by David Madilyan on 25.08.2022.
 */
inline fun <reified T : ViewModel> Fragment.createViewModel() = viewModels<T>{
    (requireActivity().applicationContext as ViewModelFactoryProvider).getViewModelFactory()
}

fun <T> Fragment.observe(liveData: LiveData<T>, observer: (T?) -> Unit) {
    liveData.observe(viewLifecycleOwner) { observer(it) }
}

fun <T> Fragment.observeOnEvent(liveData: LiveData<Event<T>>, observer: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, EventObserver { observer(it) })
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