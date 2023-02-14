package com.example.collections_and_maps.ui.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BenchmarkViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final int benchmarkType;

    public BenchmarkViewModelFactory(int benchmarkType) {
        this.benchmarkType = benchmarkType;
    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BenchmarkViewModel(benchmarkType);
    }

}
