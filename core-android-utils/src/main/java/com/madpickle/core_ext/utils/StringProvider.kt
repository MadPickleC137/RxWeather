package com.madpickle.core_ext.utils

import android.app.Application
import androidx.annotation.StringRes

/**
 * Created by David Madilyan on 27.08.2022.
 */
class StringsProvider(
    private val application: Application
) {
    fun getString(@StringRes id: Int): String = application.getString(id)
}