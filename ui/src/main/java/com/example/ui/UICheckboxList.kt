package com.example.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ui.adapter.GenericAdapter
import com.example.ui.databinding.UiCheckboxListBinding
import java.io.Serializable

data class CheckboxField(
    val id: Int, val content: String, val required: Boolean, var checked: Boolean = false
) : Serializable

class UICheckboxList @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyle: Int = 0,
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding = UiCheckboxListBinding.inflate(LayoutInflater.from(context), this, true)

    var list: List<CheckboxField>? = null
        set(value) {
            initializeRecyclerView(value)
            field = value
        }

    var onCheckListener: ((Boolean) -> Unit)? = null

    private fun initializeRecyclerView(
        checkboxFieldList: List<CheckboxField>?
    ) {
        binding.rvCheckboxList.adapter =
            object : GenericAdapter<CheckboxField>(R.layout.item_checkbox, bind = { item, holder ->
                with(holder.itemView) {
                    this.findViewById<CheckBox>(R.id.cb_item).run {
                        text = item.content
                        setOnCheckedChangeListener { _, isChecked ->
                            item.checked = isChecked

                            val nonNullList = list ?: return@setOnCheckedChangeListener

                            val allRequiredChecked = nonNullList.none { it.required && !it.checked }
                            onCheckListener?.invoke(allRequiredChecked)
                        }
                    }
                }
            }) {}.apply { this.submitList(checkboxFieldList) }
    }


}