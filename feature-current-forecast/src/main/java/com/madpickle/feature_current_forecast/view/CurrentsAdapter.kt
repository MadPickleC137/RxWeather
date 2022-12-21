package com.madpickle.feature_current_forecast.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.madpickle.core_android.getDateString
import com.madpickle.core_android.getTimeString
import com.madpickle.core_data.model.CurrentModel
import com.madpickle.feature_current_forecast.databinding.ItemCurrentBinding


/**
 * Created by David Madilyan on 15.12.2022.
 */
class CurrentsAdapter: RecyclerView.Adapter<CurrentsAdapter.CurrentItemViewHolder>() {
    var onItemUpdate: ((CurrentModel) -> Unit)? = null
    private var items = mutableListOf<CurrentModel>()

    fun initItems(list: List<CurrentModel>){
        val newList = list.toMutableList()
        val diffUtilCallback = CurrentDiffUtilCallback(items, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback, true)
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentItemViewHolder {
        return CurrentItemViewHolder(parent )
    }

    override fun onBindViewHolder(holder: CurrentItemViewHolder, position: Int) {
        holder.bind(items[position], onItemUpdate)
    }

    override fun getItemCount() = items.size

    class CurrentItemViewHolder(itemView: ViewGroup, binding: ItemCurrentBinding = ItemCurrentBinding.inflate(LayoutInflater.from(itemView.context), itemView, false)): RecyclerView.ViewHolder(binding.root){
        private val data = binding.dateCurrent
        private val icon = binding.iconCurrent
        private val temp = binding.tempCurrent
        private val region = binding.regionCurrent
        private val uf = binding.ufCurrent
        private val cloud = binding.cloudCurrent
        private val wind = binding.windCurrent
        private val humidity = binding.humidityCurrent
        private val lastUpdate = binding.lastUpdatedCurrent
        private val refreshBtn = binding.refreshCurrent

        fun bind(current: CurrentModel, onItemUpdate: ((CurrentModel) -> Unit)?){
            data.text = current.lastUpdated.getDateString()
            icon.load(current.iconUrl) {
                diskCachePolicy(CachePolicy.ENABLED)
                networkCachePolicy(CachePolicy.ENABLED)
                placeholder(com.madpickle.core_android.R.drawable.ic_clear_weather)
                transformations(CircleCropTransformation())
            }
            temp.text = current.temperature?.toString() + " ºC"
            region.text = current.region
            uf.text = current.uv?.toString()
            cloud.text = current.cloud?.toString() + " %"
            wind.text = current.windKmp?.toString() + " m/c"
            humidity.text = current.humidity?.toString() + " %"
            lastUpdate.text = String.format(itemView.context.getString(com.madpickle.core_android.R.string.last_updated), current.lastUpdated.getTimeString())
            refreshBtn.setOnClickListener {
                onItemUpdate?.invoke(current)
            }
        }
    }
}


class CurrentDiffUtilCallback(private val oldList: List<CurrentModel>,
                              private val newList: List<CurrentModel>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]
        return newItem.region == oldItem.region
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]
        return newItem.lastUpdated == oldItem.lastUpdated &&
                newItem.lastUpdatedEpoch == oldItem.lastUpdatedEpoch
    }
}
