package com.example.collections_and_maps.ui.benchmark.models;

import com.example.collections_and_maps.ui.benchmark.BenchmarkViewModelFactory;

import dagger.Component;

@Component(modules = {AppModuleTest.class})
public interface AppComponent {
    void inject(BenchmarkViewModelFactory factory);

}