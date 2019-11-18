package com.iriasan.examplecore.di

import android.app.Application

import androidx.room.Room

import com.iriasan.examplecore.data.UserRepository
import com.iriasan.examplecore.data.UserRepositoryImpl
import com.iriasan.examplecore.data.database.AppDatabase

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class])
class AppModule {


    @Provides
    @Singleton
    @Named("application.context")
     fun provideContextApplication(app: Application) = app

    @Provides
    @Singleton
     fun provideAppDatabase(application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, "db-users").build()


    @Singleton
    @Provides
     fun providesUserDao(appDatabase: AppDatabase) = appDatabase.userDao

    @Provides
    @Singleton
     fun provideUserRepository(userRepository: UserRepositoryImpl): UserRepository = userRepository

}
