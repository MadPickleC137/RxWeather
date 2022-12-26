package com.madpickle.feature_days_forecast.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.madpickle.core_android.utils.getDayOfWeek
import com.madpickle.core_android.utils.toUrlIcon
import com.madpickle.core_data.model.DayModel
import com.madpickle.feature_days_forecast.databinding.ItemDayBinding

/**
 * Created by David Madilyan on 26.12.2022.
 */
class DaysAdapter(private val onItemClick: (DayModel) -> Unit): RecyclerView.Adapter<ItemDayViewHolder>() {
    private val items = mutableListOf<DayModel>()
    private var selectedPos = 0
    fun initItems(newList: List<DayModel>){
        selectedPos = 0
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemDayViewHolder(parent)

    override fun onBindViewHolder(holder: ItemDayViewHolder, position: Int) {
        val dayModel = items[position]
        holder.binding.root.setOnClickListener {
            if(selectedPos != position){
                notifyItemChanged(selectedPos)
                onItemClick.invoke(dayModel)
                selectedPos = holder.layoutPosition
                notifyItemChanged(selectedPos)
            }
        }
        holder.binding.root.isSelected = selectedPos == position
        holder.binding.title.text = dayModel.dateForecast.getDayOfWeek()
        holder.binding.iconDay.load(dayModel.iconUrl.toUrlIcon()){
            diskCachePolicy(CachePolicy.ENABLED)
            placeholder(com.madpickle.core_android.R.drawable.ic_clear_weather)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount() = items.size
}

class ItemDayViewHolder(itemView: ViewGroup, val binding: ItemDayBinding = ItemDayBinding.inflate(
    LayoutInflater.from(itemView.context), itemView, false)): RecyclerView.ViewHolder(binding.root)
