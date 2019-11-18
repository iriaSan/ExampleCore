package com.iriasan.examplecore.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NameUser(
    @SerializedName("title")
    var titleName: String = "",

    @SerializedName("first")
    var firstName: String = "",

    @SerializedName("last")
    var lastName: String = ""
) : Parcelable