package com.example.collections_and_maps

import android.app.Application
import com.example.collections_and_maps.models.AppComponent

class App : Application() {

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
