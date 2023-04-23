package com.example.collections_and_maps.ui.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.di.BenchmarkComponent;
import com.example.collections_and_maps.di.DaggerBenchmarkComponent;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;

import javax.inject.Inject;

public class BenchmarkViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Benchmark benchmark;

    @Inject
    CollectionsBenchmark collectionsBenchmark;
    @Inject
    MapsBenchmark mapsBenchmark;

    public BenchmarkViewModelFactory(int benchmarkType) {

        BenchmarkComponent component = DaggerBenchmarkComponent.create();
        component.inject(this);

        benchmark = benchmarkType == R.string.Collections ?
                collectionsBenchmark :
                mapsBenchmark;

    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BenchmarkViewModel(benchmark);
    }

}
