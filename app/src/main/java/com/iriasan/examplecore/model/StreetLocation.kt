package com.iriasan.examplecore.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StreetLocation(
    @SerializedName("number")
    var numberStreet: Int? = 0,

    @SerializedName("name")
    var nameStreet: String = ""
) : Parcelable