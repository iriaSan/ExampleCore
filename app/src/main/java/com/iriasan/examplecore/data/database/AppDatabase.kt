package com.iriasan.examplecore.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.iriasan.examplecore.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao
}
