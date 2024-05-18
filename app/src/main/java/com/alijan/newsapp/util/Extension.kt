package com.alijan.newsapp.util

import android.view.View
import android.widget.ImageView
import com.alijan.newsapp.R
import com.bumptech.glide.Glide

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun ImageView.setImageUrl(url: String?){
        Glide
            .with(this)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.no_image)
            .into(this)
}