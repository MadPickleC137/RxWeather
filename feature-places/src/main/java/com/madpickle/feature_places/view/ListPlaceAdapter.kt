package com.madpickle.feature_places.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.madpickle.core_data.model.LocationModel
import com.madpickle.feature_places.databinding.ItemPlaceBinding

/**
 * Created by David Madilyan on 19.12.2022.
 */
class ListPlaceAdapter : RecyclerView.Adapter<ListPlaceAdapter.ItemPlaceViewHolder>() {
    var onItemClick: ((LocationModel) -> Unit)? = null
    private val differCallback = object : DiffUtil.ItemCallback<LocationModel>(){
        override fun areItemsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
            return  oldItem.region == newItem.region
        }

        override fun areContentsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
            return oldItem.lat == newItem.lat && oldItem.lon == newItem.lon
        }

    }

    private val differ = AsyncListDiffer(this, differCallback)

    fun initItems(list: List<LocationModel>){
        differ.submitList(list)
    }

    fun clearAll(){
        differ.submitList(emptyList())
    }


    class ItemPlaceViewHolder(itemView: ViewGroup,
                              private val binding: ItemPlaceBinding =
                                  ItemPlaceBinding.inflate(LayoutInflater.from(itemView.context), itemView, false)
    ): RecyclerView.ViewHolder(binding.root){
        private val name = binding.name
        private val timezone = binding.timezone
        private val country = binding.country
        var onItemClick: ((LocationModel) -> Unit)? = null

        fun bind(model: LocationModel){
            name.text = model.name
            country.text = model.country
            timezone.text = model.region
            binding.layout.setOnClickListener {
                onItemClick?.invoke(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPlaceViewHolder {
       return ItemPlaceViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemPlaceViewHolder, position: Int) {
        holder.onItemClick = onItemClick
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size
}