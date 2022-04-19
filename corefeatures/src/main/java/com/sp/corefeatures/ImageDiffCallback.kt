package com.sp.corefeatures

import androidx.recyclerview.widget.DiffUtil
import com.sp.common.entities.SearchItem

internal class ImageDiffCallback : DiffUtil.ItemCallback<SearchItem>() {

    override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return oldItem.href == newItem.href
    }

    override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return oldItem.href == newItem.href
    }
}

