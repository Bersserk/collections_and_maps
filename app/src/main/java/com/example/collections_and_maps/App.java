package com.example.collections_and_maps;

import android.app.Application;

import com.example.collections_and_maps.models.AppComponent;
import com.example.collections_and_maps.models.DaggerAppComponent;

public class App extends Application {

    private AppComponent appComponent;
    public App() {
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}