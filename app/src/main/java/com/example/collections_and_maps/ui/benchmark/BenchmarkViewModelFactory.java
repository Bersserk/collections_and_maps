package com.example.collections_and_maps.ui.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;

public class BenchmarkViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Benchmark benchmark;

    public BenchmarkViewModelFactory(int benchmarkType) {
        benchmark = benchmarkType == R.string.Collections ?
                new CollectionsBenchmark() :
                new MapsBenchmark();
    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BenchmarkViewModel(benchmark);
    }

}
