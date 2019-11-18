package com.iriasan.examplecore.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisteredUser(
    @SerializedName("date")
    var dateRegister: String = ""
) : Parcelable
