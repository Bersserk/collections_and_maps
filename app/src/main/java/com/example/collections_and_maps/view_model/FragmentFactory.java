package com.example.collections_and_maps.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.collections_and_maps.models.benchmarks.DataFilter;

public class FragmentFactory extends ViewModelProvider.NewInstanceFactory {

    private final int namePagerView;

    public FragmentFactory(int namePagerView) {
        this.namePagerView = namePagerView;
    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FragmentViewModel(new DataFilter(namePagerView));
    }
}
