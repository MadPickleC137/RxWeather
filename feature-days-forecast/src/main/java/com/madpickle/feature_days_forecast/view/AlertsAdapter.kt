package com.madpickle.feature_days_forecast.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.madpickle.core_android.utils.parseAlertDate
import com.madpickle.core_data.model.AlertModel
import com.madpickle.feature_days_forecast.databinding.ItemAlertBinding

/**
 * Created by David Madilyan on 25.12.2022.
 */
class AlertsAdapter: RecyclerView.Adapter<ItemAlertViewHolder>() {
    private val items = mutableListOf<AlertModel>()
    fun initAlerts(newList: List<AlertModel>){
        items.clear()
        items.addAll(newList.filter { !it.desc.isNullOrEmpty() })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemAlertViewHolder(parent)

    override fun onBindViewHolder(holder: ItemAlertViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}

class ItemAlertViewHolder(itemView: ViewGroup, binding: ItemAlertBinding = ItemAlertBinding.inflate(
    LayoutInflater.from(itemView.context), itemView, false)): RecyclerView.ViewHolder(binding.root){
    private val title = binding.title
    private val desc = binding.desc
    private val start = binding.timeStart
    private val end = binding.timeEnd

    fun bind(item: AlertModel){
        title.text = item.event
        desc.text = item.desc
        start.text = item.effective.parseAlertDate("С:")
        end.text = item.expires.parseAlertDate("До:")
    }
}