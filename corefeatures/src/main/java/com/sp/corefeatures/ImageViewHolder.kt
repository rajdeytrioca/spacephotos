package com.sp.corefeatures

import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sp.common.entities.SearchItem
import com.sp.corefeatures.databinding.ImageListBinding

class ImageViewHolder(val binding: ImageListBinding,val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: SearchItem?) {
        binding.title.text = item?.data?.get(0)?.title
        binding.desc.text = item?.data?.get(0)?.description
        val link  = item?.links?.get(0)?.href
        Glide.with(context)
            .load(Uri.parse(link))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .circleCrop()
            .placeholder(R.drawable.placeholder)
            .into(binding.ivPhoto)
    }

}
