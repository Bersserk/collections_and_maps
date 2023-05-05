package com.example.collections_and_maps.models;

import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Named("collection")
    public Benchmark provideCollections() {
        return new CollectionsBenchmark();
    }

    @Provides
    @Named("maps")
    public Benchmark provideMaps() {
        return new MapsBenchmark();
    }

}
