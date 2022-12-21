package com.madpickle.core_android.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler


/**
 * Created by David Madilyan on 22.12.2022.
 */
class WrapperLinearLayoutManager @JvmOverloads constructor(context: Context?,
                                                           attrs: AttributeSet? = null,
                                                           defStyleAttr: Int = 0,
                                                           defStyleRes: Int = 0) : LinearLayoutManager(context, attrs, defStyleAttr, defStyleRes) {
    override fun onLayoutChildren(recycler: Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Log.d("WARNING", "Meet a IndexOutOfBoundsException in RecyclerView")
        }
    }
}