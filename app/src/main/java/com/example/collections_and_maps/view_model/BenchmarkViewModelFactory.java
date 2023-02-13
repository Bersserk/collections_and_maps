package com.example.collections_and_maps.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.CollectionsComputeTime;
import com.example.collections_and_maps.models.benchmarks.ComputeTime;
import com.example.collections_and_maps.models.benchmarks.MapsComputeTime;

public class BenchmarkViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final int benchmarkType;

    public BenchmarkViewModelFactory(int benchmarkType) {
        this.benchmarkType = benchmarkType;
    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BenchmarkViewModel(getClassFragment());
    }

    private ComputeTime getClassFragment (){
        return benchmarkType == R.string.Collections ?
                new CollectionsComputeTime():
                new MapsComputeTime();
    }
}
