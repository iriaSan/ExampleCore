package com.iriasan.examplecore.di

import com.iriasan.examplecore.ui.main.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributesMainActivity(): MainActivity

}


