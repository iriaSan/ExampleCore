package com.iriasan.examplecore.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.iriasan.examplecore.model.LocationUser
import com.iriasan.examplecore.model.NameUser
import com.iriasan.examplecore.model.PictureUser
import com.iriasan.examplecore.model.RegisteredUser
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    var idUser: Int = 0,

    @SerializedName("gender")
    var genderUser: String = "",

    @Embedded
    @SerializedName("name")
    var nameUser: NameUser = NameUser(),

    @Embedded
    @SerializedName("location")
    var locationUser: LocationUser? = null,

    @SerializedName("email")
    var emailUser: String = "",

    @Embedded
    @SerializedName("registered")
    var registeredUser: RegisteredUser = RegisteredUser(),

    @SerializedName("phone")
    var phoneUser: String = "",

    @SerializedName("cell")
    var cellUser: String = "",

    @Embedded
    @SerializedName("picture")
    var pictureUser: PictureUser = PictureUser()
) : Parcelable