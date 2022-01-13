package com.example.driversapptest.presentation.di


import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideDispatchersProvider() = DispatchersProvider(
        io = Dispatchers.IO,
        default = Dispatchers.Default,
        main = Dispatchers.Main
    )
}