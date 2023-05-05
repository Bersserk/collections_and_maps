package com.example.collections_and_maps.app;

import android.app.Application;

import com.example.collections_and_maps.di.AppComponent;
import com.example.collections_and_maps.di.DaggerAppComponent;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
