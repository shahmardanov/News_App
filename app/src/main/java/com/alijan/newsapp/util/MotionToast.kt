package com.alijan.newsapp.util

import android.app.Activity
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.alijan.newsapp.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

fun toastError(context: Context?, title: String, message: String){
    context?.let {
        MotionToast.createToast(
            it as Activity,
            title,
            message,
            MotionToastStyle.ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            context?.let { it1 -> ResourcesCompat.getFont(it1, R.font.poppins_regular) })
    }
}