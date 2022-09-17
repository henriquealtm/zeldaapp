package com.example.zeldaapp.category.presentation.item.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zeldaapp.R
import com.example.zeldaapp.category.domain.model.CategoryItem
import com.example.zeldaapp.databinding.CategoryItemBinding

internal class CategoryItemListAdapter(
    private val onItemClick: (CategoryItem) -> Unit,
) : ListAdapter<CategoryItem, CategoryItemListAdapter.DataHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        return DataHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.category_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.binding.run {
            val categoryItem = getItem(position)
            item = categoryItem
            root.setOnClickListener { onItemClick(categoryItem) }
        }
    }

    inner class DataHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private object DiffCallback : DiffUtil.ItemCallback<CategoryItem>() {

        override fun areItemsTheSame(
            oldItem: CategoryItem,
            newItem: CategoryItem
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CategoryItem,
            newItem: CategoryItem
        ) = oldItem == newItem
    }

}