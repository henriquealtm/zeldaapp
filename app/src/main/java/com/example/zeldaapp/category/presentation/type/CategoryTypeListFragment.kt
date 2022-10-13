package com.example.zeldaapp.category.presentation.type

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.commons.base.BaseFragment
import com.example.ui.CheckboxField
import com.example.zeldaapp.R
import com.example.zeldaapp.databinding.FragmentCategoryTypeListBinding

import java.io.Serializable

enum class FieldType {
    CHECKBOX, RADIO
}

// Response
data class CustomField(
    val id: Int,
    val label: String,
    val content: String,
    val type: FieldType,
    val options: List<String>,
    val required: Boolean
) : Serializable

// Request
data class ResponseCustomField(
    val id: Int,
    val response: String,
)

class CategoryTypeListFragment : BaseFragment<FragmentCategoryTypeListBinding>(
    R.layout.fragment_category_type_list
) {

    private val listCustomFieldApiResponse = listOf(
        CustomField(
            id = 1,
            label = "",
            content = "CHECK 1111",
            type = FieldType.CHECKBOX,
            options = listOf("Não aceito", "Aceito"),
            required = false,
        ),
        CustomField(
            id = 2,
            label = "",
            content = "CHECK 2222",
            type = FieldType.CHECKBOX,
            options = listOf("Não aceito", "Aceito"),
            required = true,
        ),
        CustomField(
            id = 3,
            label = "",
            content = "RADIO 3333",
            type = FieldType.RADIO,
            options = listOf("Não aceito", "Aceito"),
            required = true,
        ),
    )

    private val checkBoxList = listCustomFieldApiResponse.filter { it.type == FieldType.CHECKBOX }

    private val checkBoxOptions = checkBoxList.first().options

    private fun List<CustomField>.mapToComponent() = map {
        CheckboxField(
            it.id, it.content, it.required
        )
    }

    private fun List<CheckboxField>.mapToRequest() = map {
        ResponseCustomField(
            id = it.id,
            response = it.getOptionValue(),
        )
    }

    private fun CheckboxField.getOptionValue() =
        if (checked) checkBoxOptions[1] else checkBoxOptions[0]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            checkboxList.list = checkBoxList.mapToComponent()

            checkboxList.onCheckListener = { allRequiredChecked ->
                materialButton.isEnabled = allRequiredChecked
            }

            materialButton.isEnabled = checkboxList.list?.none { it.required } == true

            materialButton.setOnClickListener {
                val requestList = checkboxList.list?.mapToRequest()
                Toast.makeText(
                    requireContext(), requestList?.toString(), Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun setupViewModel() {

    }

    override fun setupObservers() {
    }

    private fun setupAdapter(list: List<String>) {
    }

    private fun onCategoryTypeOnClick(categoryType: String) {
    }

}