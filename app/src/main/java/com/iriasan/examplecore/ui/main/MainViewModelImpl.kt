package com.iriasan.examplecore.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import com.cexmobility.corekotlin.data.general.Resource
import com.cexmobility.corekotlin.ui.BaseViewModel

import com.iriasan.examplecore.data.UserRepository
import com.iriasan.examplecore.model.User
import com.iriasan.woomtest.ui.main.MainViewModel

import javax.inject.Inject

class MainViewModelImpl @Inject
constructor(application: Application, private val userRepository: UserRepository) :
    BaseViewModel(application), MainViewModel {

    override fun getAllUsers(page: Int?): LiveData<Resource<List<User>>> {
        return userRepository.getUsersList(page)
    }
}
