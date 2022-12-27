package com.madpickle.feature_current_forecast.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.madpickle.core_android.utils.getDateString
import com.madpickle.core_android.utils.getTimeString
import com.madpickle.core_android.utils.toUrlIcon
import com.madpickle.core_data.model.CurrentModel
import com.madpickle.feature_current_forecast.R
import com.madpickle.feature_current_forecast.databinding.ItemCurrentBinding


/**
 * Created by David Madilyan on 15.12.2022.
 */
class CurrentsAdapter(
    private val onItemSelect: (String) -> Unit,
    private val onItemUpdate: (CurrentModel, Int) -> Unit,
    private val onItemDelete: (CurrentModel, Int) -> Unit): RecyclerView.Adapter<CurrentsAdapter.CurrentItemViewHolder>() {
    private val items = mutableListOf<CurrentModel>()

    fun initItems(list: List<CurrentModel>){
        if(items.size != list.size){
            items.clear()
            items.addAll(list)
            notifyItemRangeInserted(0, itemCount)
        }
    }

    fun updatedItem(current: CurrentModel, index: Int) {
        if(items.isNotEmpty()){
            items[index] = current
            notifyItemChanged(index)
        }
    }

    fun deleteItem(current: CurrentModel, index: Int) {
        items.remove(current)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentItemViewHolder {
        return CurrentItemViewHolder(parent, onItemSelect, onItemUpdate, onItemDelete)
    }

    override fun onBindViewHolder(holder: CurrentItemViewHolder, position: Int) {
        holder.bind(items[position], position)
    }
    override fun getItemCount() = items.size

    class CurrentItemViewHolder(private val itemView: ViewGroup,
                                private val onItemSelect: (String) -> Unit,
                                private val onItemUpdate: (CurrentModel, Int) -> Unit,
                                private val onItemDelete: (CurrentModel, Int) -> Unit,
                                binding: ItemCurrentBinding = ItemCurrentBinding.inflate(LayoutInflater.from(itemView.context), itemView, false)): RecyclerView.ViewHolder(binding.root){
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
        private val btnPopupMenu = binding.showMenuBtn
        private val root = binding.root

        fun bind(current: CurrentModel, index: Int){
            data.text = current.lastUpdated.getDateString()
            icon.load(current.iconUrl.toUrlIcon()) {
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
                onItemUpdate.invoke(current, layoutPosition)
                it.animate()
                    .setInterpolator(LinearInterpolator())
                    .setDuration(500L)
                    .rotation(360F)
                    .start()
            }
            root.setOnClickListener {
                onItemSelect.invoke(current.region)
            }
            initPopupMenu(current, index)
        }

        private fun initPopupMenu(current: CurrentModel, index: Int){
            val popup = PopupMenu(itemView.context, btnPopupMenu)
            popup.menuInflater.inflate(R.menu.dropdown_menu, popup.menu)
            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.delete -> { onItemDelete.invoke(current, index) }
                    R.id.update -> { onItemUpdate.invoke(current, index) }
                }
                return@setOnMenuItemClickListener true
            }
            btnPopupMenu.setOnClickListener {
                popup.show()
            }
        }
    }
}
