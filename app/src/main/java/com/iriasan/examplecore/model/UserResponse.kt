package com.iriasan.examplecore.model

import com.google.gson.annotations.SerializedName
import com.iriasan.examplecore.model.User

data class UserResponse(
    @SerializedName("results")
    var listUsers: List<User>? = null
)
