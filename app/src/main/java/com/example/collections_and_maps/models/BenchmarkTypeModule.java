package com.example.collections_and_maps.models;

import com.example.collections_and_maps.models.benchmarks.BenchmarkType;
import com.example.collections_and_maps.models.benchmarks.FragmentType;

import dagger.Module;
import dagger.Provides;

@Module
public class BenchmarkTypeModule {
    @Provides
    BenchmarkType provideBenchmarkType (){
        return new FragmentType();
    }

}
