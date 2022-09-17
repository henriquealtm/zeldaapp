package com.example.ui

import android.animation.LayoutTransition
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ui.databinding.UiLabeledDescriptionBinding

class UILabeledDescription @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyle: Int = 0,
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding =
        UiLabeledDescriptionBinding.inflate(LayoutInflater.from(context), this, true)

    var label: String? = null
        set(value) {
            binding.tvLabel.text = value
            field = value
        }

    var description: String? = null
        set(value) {
            binding.tvDescription.text = value
            field = value

            updateComponentVisibility(value)
        }

    private fun updateComponentVisibility(currentDescription: String?) {
        if (currentDescription.isNullOrEmpty()) hideComponent() else showComponent()
    }

    private fun hideComponent() {
        visibility = View.GONE
    }

    private fun showComponent() {
        visibility = View.VISIBLE
    }

    init {
        initView()
        configurationAttr()
    }

    private fun initView() {
        layoutTransition = LayoutTransition()
    }

    private fun configurationAttr() {
        val style = context.obtainStyledAttributes(
            attrs,
            R.styleable.UILabeledDescription,
            defStyle,
            0
        )
        updateLabel(style.getString(R.styleable.UILabeledDescription_label))
        updateDescription(style.getString(R.styleable.UILabeledDescription_description))
        style.recycle()
    }

    private fun updateLabel(value: String?) {
        label = value
    }

    private fun updateDescription(value: String?) {
        description = value
    }

}