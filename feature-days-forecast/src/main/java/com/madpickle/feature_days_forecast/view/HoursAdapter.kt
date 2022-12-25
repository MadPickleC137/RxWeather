package com.madpickle.feature_days_forecast.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.madpickle.core_android.toCelsiusString
import com.madpickle.core_android.utils.getTimeString
import com.madpickle.core_android.utils.toUrlIcon
import com.madpickle.core_data.model.HourModel
import com.madpickle.feature_days_forecast.databinding.ItemHourBinding

/**
 * Created by David Madilyan on 25.12.2022.
 */
class HoursAdapter(private val onItemClick: ((HourModel) -> Unit)): RecyclerView.Adapter<ItemHourViewHolder>() {
    private val items = mutableListOf<HourModel>()

    fun initHours(newList: List<HourModel>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemHourViewHolder(parent)

    override fun onBindViewHolder(holder: ItemHourViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount() = items.size
}

class ItemHourViewHolder(itemView: ViewGroup,private val binding: ItemHourBinding = ItemHourBinding.inflate(
    LayoutInflater.from(itemView.context), itemView, false)): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HourModel, onItemClick: ((HourModel) -> Unit)?){
        binding.icon.load(item.iconUrl.toUrlIcon()) {
            diskCachePolicy(CachePolicy.ENABLED)
            networkCachePolicy(CachePolicy.ENABLED)
            placeholder(com.madpickle.core_android.R.drawable.ic_clear_weather)
            transformations(CircleCropTransformation())
        }
        binding.time.text = item.time.getTimeString()
        binding.temperature.text = item.temperature.toCelsiusString()
        binding.root.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}
