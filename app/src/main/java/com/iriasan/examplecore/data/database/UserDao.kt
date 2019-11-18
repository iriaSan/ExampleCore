package com.iriasan.examplecore.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.iriasan.examplecore.model.User

import androidx.room.OnConflictStrategy.REPLACE

@Dao
abstract class UserDao {

    @Query("SELECT * FROM user")
    abstract fun allUsers(): LiveData<List<User>>

    @Query("DELETE FROM user")
    abstract fun deleteUserTable()

    @Insert(onConflict = REPLACE)
    abstract fun insertAllUsers(userList: List<User>)

    @Query("SELECT * FROM user where idUser=:id")
    abstract fun getUserById(id: Int): LiveData<User>


}
