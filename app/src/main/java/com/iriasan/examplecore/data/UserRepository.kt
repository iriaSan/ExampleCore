package com.iriasan.examplecore.data

import androidx.lifecycle.LiveData
import com.cexmobility.corekotlin.data.general.Resource

import com.iriasan.examplecore.model.User

interface UserRepository {

    fun getUsersList(page: Int?): LiveData<Resource<List<User>>>
}
