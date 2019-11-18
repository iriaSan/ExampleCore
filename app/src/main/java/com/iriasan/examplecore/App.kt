package com.iriasan.examplecore

import android.app.Activity
import android.app.Application
import com.iriasan.examplecore.di.DaggerAppComponent


import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber

class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()

        // Dagger initialization
        DaggerAppComponent.builder().application(this).build().inject(this)


        // Timber Log initialization
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ':'.toString() + element.lineNumber
                }
            })
        }
    }

    override fun activityInjector() = activityInjector


}
