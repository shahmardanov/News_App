package com.alijan.newsapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
): Parcelable