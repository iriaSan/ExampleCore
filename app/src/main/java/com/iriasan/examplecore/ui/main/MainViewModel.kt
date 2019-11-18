package com.iriasan.woomtest.ui.main

import androidx.lifecycle.LiveData
import com.cexmobility.corekotlin.data.general.Resource
import com.iriasan.examplecore.model.User


interface MainViewModel {

    fun getAllUsers(page: Int?): LiveData<Resource<List<User>>>
}
