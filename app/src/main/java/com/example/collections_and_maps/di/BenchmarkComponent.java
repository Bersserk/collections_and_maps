package com.example.collections_and_maps.di;

import com.example.collections_and_maps.ui.benchmark.BenchmarkViewModelFactory;

import dagger.Component;

@Component
public interface BenchmarkComponent {

    void inject (BenchmarkViewModelFactory benchmarkViewModelFactory);
}
