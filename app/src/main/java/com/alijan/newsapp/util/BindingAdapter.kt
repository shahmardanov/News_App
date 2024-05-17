package com.alijan.newsapp.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("setImageFromLocal")
fun setImageUrl(imageView: ImageView, image: Int){
    imageView.setImageResource(image)
}