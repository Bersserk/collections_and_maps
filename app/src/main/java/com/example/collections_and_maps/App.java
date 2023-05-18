package com.example.collections_and_maps;

import android.app.Application;

import com.example.collections_and_maps.models.AppComponent;
import com.example.collections_and_maps.models.DaggerAppComponent;

public class App extends Application {

    private final AppComponent appComponent = DaggerAppComponent.create();
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
