package com.sp.corefeatures.fragments

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sp.corefeatures.R

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private lateinit var views: Views

    private class Views {
        lateinit var title: TextView
        lateinit var desc: TextView
        lateinit var photo: ImageView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        views = Views().also {
            it.title = view.findViewById(R.id.title)
            it.desc = view.findViewById(R.id.desc)
            it.photo = view.findViewById(R.id.ivPhoto)
        }

        val title = arguments?.getString("title")
        val desc = arguments?.getString("desc")
        val link = arguments?.getString("link")

        views.title.text=title
        views.desc.text=desc

        context?.let {
            Glide.with(it)
                .load(Uri.parse(link))
                .fitCenter()
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(views.photo)
        }
    }

}