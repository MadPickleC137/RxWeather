package com.madpickle.feature_current_forecast.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.madpickle.core_data.model.CurrentModel
import com.madpickle.core_android.getDateString
import com.madpickle.core_android.getTimeString
import com.madpickle.feature_current_forecast.R
import com.madpickle.feature_current_forecast.databinding.ItemCurrentBinding

/**
 * Created by David Madilyan on 15.12.2022.
 */
class CurrentsAdapter: RecyclerView.Adapter<CurrentsAdapter.CurrentItemViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<CurrentModel>(){
        override fun areItemsTheSame(oldItem: CurrentModel, newItem: CurrentModel): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CurrentModel, newItem: CurrentModel): Boolean {
            return oldItem.region == newItem.region &&
                    oldItem.temperature == newItem.temperature && oldItem.feelsLike == newItem.feelsLike
        }

    }

    private val differ = AsyncListDiffer(this, differCallback)

    fun initItems(list: List<CurrentModel>){
        differ.submitList(list)
    }

    fun clearAll(){
        differ.submitList(emptyList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentItemViewHolder {
        return CurrentItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CurrentItemViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

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

        fun bind(current: CurrentModel){
            data.text = current.lastUpdated.getDateString()
            icon.load(current.iconUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_clear_weather)
                transformations(CircleCropTransformation())
            }
            temp.text = current.temperature?.toString()
            region.text = current.region
            uf.text = current.uv?.toString()
            cloud.text = current.cloud?.toString()
            wind.text = current.windKmp?.toString()
            humidity.text = current.humidity?.toString()
            lastUpdate.text = String.format(itemView.context.getString(com.madpickle.core_android.R.string.last_updated), current.lastUpdated.getTimeString())
        }
    }
}
