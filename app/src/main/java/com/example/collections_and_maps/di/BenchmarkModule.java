package com.example.collections_and_maps.di;

import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;

import dagger.Module;
import dagger.Provides;

@Module
public class BenchmarkModule {

    @Provides
    public CollectionsBenchmark provideCollections() {
        return new CollectionsBenchmark();
    }

    @Provides
    public MapsBenchmark provideMaps() {
        return new MapsBenchmark();
    }

}
