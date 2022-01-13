package com.example.driversapptest.presentation.di

import android.app.Application
import android.content.Context
import com.example.driversapptest.presentation.BaseApplication
import com.example.driversapptest.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            NetworkModule::class,
            CoreModule::class
        ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
                @BindsInstance application: Application,
                @BindsInstance applicationContext: Context
        ): ApplicationComponent
    }
    fun inject(mainActivity: MainActivity?)

    fun inject(baseApplication: BaseApplication)
}