package com.example.collections_and_maps.ui.benchmark.models;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.ui.benchmark.BenchmarkViewModelFactory;

import org.junit.runner.RunWith;

import dagger.Component;

@RunWith(AndroidJUnit4.class)
@Component(modules = {AppModuleTest.class})
public interface AppComponentTest {
    void inject(BenchmarkViewModelFactory factory);
}
