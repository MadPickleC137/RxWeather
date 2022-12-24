package com.madpickle.core_android.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.madpickle.core_android.databinding.ItemInfoViewBinding

/**
 * Created by David Madilyan on 25.12.2022.
 */
class ItemInfoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle)  {

    private val binding =  ItemInfoViewBinding.inflate(LayoutInflater.from(context), this, true)

    private fun setTitle(title: String){
        binding.title.text = title
    }
    private fun setIconStart(iconRes: Int = 0){
        if(iconRes == 0){
            binding.title.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        }else{
            val icon = ContextCompat.getDrawable(context, iconRes)
            binding.title.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)
        }
    }

    private fun showDivider(visible: Boolean){
        binding.divider.isVisible = visible
    }

    private fun resetViews(){
        binding.title.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        binding.title.text = ""
        binding.divider.isVisible = false
    }
    fun initItemInfoView(vararg properties: PropertyView){
        resetViews()
        properties.forEach { propertyView ->
            when(propertyView){
                is PropertyView.Title ->  {
                    setTitle(propertyView.t)
                    setIconStart(propertyView.iconResId)
                }
                is PropertyView.Divider -> {
                    showDivider(propertyView.visible)
                }
            }
        }
    }

    sealed class PropertyView {
        data class Title(val t: String, val iconResId: Int = 0) : PropertyView()
        data class Divider(val visible: Boolean = true) : PropertyView()
    }
}
