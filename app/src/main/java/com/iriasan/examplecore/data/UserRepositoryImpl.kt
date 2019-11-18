package com.iriasan.examplecore.data

import androidx.lifecycle.LiveData
import com.cexmobility.corekotlin.data.api.ApiGenerator
import com.cexmobility.corekotlin.data.general.AppExecutors
import com.cexmobility.corekotlin.data.general.NetworkBoundResource
import com.cexmobility.corekotlin.data.general.RateLimiter
import com.cexmobility.corekotlin.data.general.Resource
import com.iriasan.examplecore.BuildConfig
import com.iriasan.examplecore.data.api.ApiService

import com.iriasan.examplecore.model.User
import com.iriasan.examplecore.model.UserResponse
import com.iriasan.examplecore.data.database.UserDao
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserRepositoryImpl @Inject
constructor(private val mExecutors: AppExecutors, private val userDao: UserDao) : UserRepository {
    private val rateLimit = RateLimiter<String>(30, TimeUnit.MINUTES)
    private val apiGenerator: ApiGenerator<ApiService> = ApiGenerator(ApiService::class.java)
    private val mExecutor = Executors.newSingleThreadExecutor()


    override fun getUsersList(page: Int?): LiveData<Resource<List<User>>> {
        return object : NetworkBoundResource<List<User>, UserResponse>(mExecutors) {

            override fun saveCallResult(item: UserResponse?) {
                item!!.listUsers?.let { userDao.insertAllUsers(it) }
            }

            override fun shouldFetch(data: List<User>): Boolean{
                 return true
            }

            override fun loadFromDb()= userDao.allUsers()

            override fun createCall() =
                apiGenerator.createService(BuildConfig.BASE_URL).getListOfUsers(page)

            override fun onFetchFailed() {
            }
        }.asLiveData()
    }
}
