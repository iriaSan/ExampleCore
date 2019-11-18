package com.iriasan.examplecore.model

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationUser(
    @SerializedName("street")
    @Embedded
    var streetLocation: StreetLocation = StreetLocation(),

    @SerializedName("city")
    var cityLocation: String = "",

    @SerializedName("state")
    var stateLocation: String = ""
) : Parcelable


