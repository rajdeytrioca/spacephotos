package com.sp.corefeatures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sp.common.entities.SearchItem
import com.sp.corefeatures.databinding.ImageListBinding

class ImageAdapter(diffCallback: DiffUtil.ItemCallback<SearchItem>, private val action : (SearchItem?)->Unit) :
    PagingDataAdapter<SearchItem, ImageViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        return ImageViewHolder(ImageListBinding
            .inflate(LayoutInflater.from(parent.context),
            parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            action.invoke(item)
        }
    }
}