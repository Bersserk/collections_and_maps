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

    private final int benchmarkType;

    @Inject
    @Named("collection")
    Benchmark collectionsBenchmark;

    @Inject
    @Named("maps")
    Benchmark mapsBenchmark;

    public BenchmarkViewModelFactory(int benchmarkType) {
        this.benchmarkType = benchmarkType;

        App.getInstance().getAppComponent().inject(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == BenchmarkViewModel.class) {
            Benchmark benchmark;
            if (benchmarkType == R.string.Collections) {
                benchmark = collectionsBenchmark;
            } else if (benchmarkType == R.string.Maps) {
                benchmark = mapsBenchmark;
            } else {
                throw new RuntimeException("illegal class value");
            }
            return (T) new BenchmarkViewModel(benchmark);
        } else {
            throw new IllegalArgumentException("Unsupported ViewModel class: " + modelClass.getName());
        }
    }
}

