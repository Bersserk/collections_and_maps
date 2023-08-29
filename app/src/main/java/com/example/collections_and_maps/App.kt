package com.example.collections_and_maps

import android.app.Application
import com.example.collections_and_maps.models.AppComponent
import com.example.collections_and_maps.models.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        private var appComponent: AppComponent? = null

        @JvmStatic
        fun setAppComponent(component: AppComponent?) {
            appComponent = component
        }

        @JvmStatic
        fun getAppComponent(): AppComponent? {
            return appComponent
        }
    }
}
