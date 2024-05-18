package com.alijan.newsapp.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("setImageFromLocal")
fun setImageLocal(imageView: ImageView, image: Int){
    imageView.setImageResource(image)
}

@BindingAdapter("setImageFromUrl")
fun setImageUrl(imageView: ImageView, url: String?){
    url?.let {
        imageView.setImageUrl(it)
    }
}