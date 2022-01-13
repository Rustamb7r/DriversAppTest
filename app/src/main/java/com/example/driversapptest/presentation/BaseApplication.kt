package com.example.driversapptest.presentation

import android.app.Application
import com.example.driversapptest.presentation.di.ApplicationComponent
import com.example.driversapptest.presentation.di.DaggerApplicationComponent

class BaseApplication : Application() {

    var appComponent: ApplicationComponent = DaggerApplicationComponent
            .factory()
            .create(
                    application = this,
                    applicationContext = this
            )
}