package com.example.collections_and_maps.ui.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.collections_and_maps.App;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Benchmark;

import javax.inject.Inject;
import javax.inject.Named;

public class BenchmarkViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @Inject
    @Named("collection")
    Benchmark collectionsBenchmark;

    @Inject
    @Named("maps")
    Benchmark mapsBenchmark;

    private final int benchmarkType;

    public BenchmarkViewModelFactory(int benchmarkType) {
        this.benchmarkType = benchmarkType;

        new App().getAppComponent().inject(this);

    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BenchmarkViewModel.class)) {
            return (T) new BenchmarkViewModel(benchmarkType == R.string.Collections
                    ? collectionsBenchmark
                    : mapsBenchmark);
        } else {
            throw new IllegalArgumentException("Unsupported ViewModel class: " + modelClass.getName());
        }
    }
}
