package com.example.collections_and_maps.models;

import com.example.collections_and_maps.ui.benchmark.BenchmarkViewModelFactory;

import dagger.Component;

@Component(modules = {AppModule.class, BenchmarkTypeModule.class})
public interface AppComponent {
    void inject(BenchmarkViewModelFactory benchmarkViewModelFactory);
}
