package com.example.collections_and_maps.models

import com.example.collections_and_maps.models.benchmarks.Benchmark
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark
import com.example.collections_and_maps.models.benchmarks.MapsBenchmark
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
open class AppModule {
    @Provides
    @Named("collection")
    open fun provideCollections(): Benchmark {
        return CollectionsBenchmark()
    }

    @Provides
    @Named("maps")
    open fun provideMaps(): Benchmark {
        return MapsBenchmark()
    }

}