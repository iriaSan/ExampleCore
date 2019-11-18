package com.iriasan.examplecore.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PictureUser(
    @SerializedName("large")
    var largePicture: String = "",

    @SerializedName("medium")
    var mediumPicture: String = "",

    @SerializedName("thumbnail")
    var thumbnailPicture: String = ""
) : Parcelable