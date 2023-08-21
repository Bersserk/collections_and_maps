package com.example.collections_and_maps.ui.benchmark.models;

import com.example.collections_and_maps.models.AppModule;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.ui.benchmark.models.benchmarks.CollectionsBenchmarkTest;
import com.example.collections_and_maps.ui.benchmark.models.benchmarks.MapsBenchmarkTest;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModuleTest extends AppModule {

    @Provides
    @Named("collection")
    public Benchmark provideCollections() {
        return new CollectionsBenchmarkTest();
    }

    @Provides
    @Named("maps")
    public Benchmark provideMaps() {
        return new MapsBenchmarkTest();
    }

}
