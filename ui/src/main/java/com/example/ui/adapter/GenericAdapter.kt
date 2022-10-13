package com.example.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T : Any>(
    @LayoutRes private val layoutId: Int,
    inline val bind: (item: T, holder: BaseViewHolder) -> Unit
) :
    ListAdapter<T, BaseViewHolder>(BaseItemCallback<T>()) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(getItem(position), holder)
    }

    override fun getItemViewType(position: Int) = layoutId
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(
            viewType, parent, false
        )
        return BaseViewHolder(root as ViewGroup)
    }
}

class BaseViewHolder(container: ViewGroup) : RecyclerView.ViewHolder(container)

class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}