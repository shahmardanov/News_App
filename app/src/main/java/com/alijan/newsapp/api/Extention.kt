package com.alijan.newsapp.api

import android.view.View

class Extension {

    fun View.toVisibility() {
        this.visibility = View.VISIBLE
    }

    fun View.toGone() {
        this.visibility = View.GONE
    }
}