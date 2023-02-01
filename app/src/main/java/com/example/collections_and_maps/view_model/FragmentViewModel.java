package com.example.collections_and_maps.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.List;

public class FragmentViewModel extends ViewModel {

    private MutableLiveData<List<ResultItem>> liveDataResultItem;

    public MutableLiveData<List<ResultItem>> getLiveDataResultItem() {
        if (liveDataResultItem == null) {
            liveDataResultItem = new MutableLiveData<List<ResultItem>>();
        }
        return liveDataResultItem;
    }

    public FragmentViewModel(MutableLiveData<List<ResultItem>> resultList) {
        liveDataResultItem = resultList;
    }

}

