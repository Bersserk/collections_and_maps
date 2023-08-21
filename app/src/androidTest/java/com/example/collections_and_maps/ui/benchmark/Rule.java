package com.example.collections_and_maps.ui.benchmark;

import android.app.Application;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.App;
import com.example.collections_and_maps.models.AppModule;
import com.example.collections_and_maps.models.DaggerAppComponent;
import com.example.collections_and_maps.ui.MainActivity;
import com.example.collections_and_maps.ui.benchmark.models.AppComponent;
import com.example.collections_and_maps.ui.benchmark.models.AppModuleTest;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Rule extends Application {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

//    @BeforeClass
//    public static void set() {
//        AppComponent appComponent =
//                DaggerAppComponent.builder().appModule(new AppModuleTest()).build();
//        App.getInstance().getAppComponent().inject(new BenchmarkViewModelFactory());
//    }
}
