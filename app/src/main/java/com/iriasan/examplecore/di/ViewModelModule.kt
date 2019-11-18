package com.iriasan.examplecore.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cexmobility.corekotlin.di.ViewModelKey
import com.cexmobility.corekotlin.ui.BaseViewModelFactory

import com.iriasan.examplecore.ui.main.MainViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModelImpl::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModelImpl): ViewModel
}
