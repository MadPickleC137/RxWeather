package com.madpickle.core_android.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.madpickle.core_android.R
import com.madpickle.core_android.databinding.ResultViewBinding

/**
 * Created by David Madilyan on 14.12.2022.
 */
class ResultView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val binding = ResultViewBinding.inflate(LayoutInflater.from(context), this, true)
    init {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ResultView, defStyle, 0)
        setIcon(a.getResourceId(R.styleable.ResultView_icon, R.drawable.ic_find))
        setTitle(a.getText(R.styleable.ResultView_title))
        setText(a.getText(R.styleable.ResultView_text))
    }

    private fun setTitle(text: CharSequence?) {
        binding.title.text = text
    }

    private fun setText(text: CharSequence?) {
        binding.text.text = text
    }

    private fun setIcon(resId: Int){
        binding.icon.setImageResource(resId)
    }

    private fun hideAllViews(){
        binding.icon.isVisible = false
        binding.text.isVisible = false
        binding.title.isVisible = false
    }

    fun initResultView(vararg properties: PropertyView){
        hideAllViews()
        properties.forEach { propertyView ->
            when(propertyView){
                is PropertyView.Icon ->  {
                    binding.icon.isVisible = true
                    setIcon(propertyView.resId)
                }
                is PropertyView.SubTitleText -> {
                    binding.title.isVisible = true
                    setText(propertyView.t)
                }
                is PropertyView.TitleText -> {
                    binding.text.isVisible = true
                    setTitle(propertyView.t)
                }
            }
        }
    }

    sealed class PropertyView {
        data class TitleText(val t: String) : PropertyView()
        data class SubTitleText(val t: String) : PropertyView()
        data class Icon(val resId: Int) : PropertyView()
    }
}