package com.example.collections_and_maps.di;

import com.example.collections_and_maps.ui.benchmark.BenchmarkViewModelFactory;

import dagger.Component;

@Component(modules = {BenchmarkModule.class})
public interface AppComponent {
    void inject(BenchmarkViewModelFactory benchmarkViewModelFactory);
}
