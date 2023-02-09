package com.example.collections_and_maps.view_model.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.domain.benchmarks.ResultItem;
import com.example.collections_and_maps.view_model.interfases.DefaultList;
import com.example.collections_and_maps.domain.benchmarks.CheckedItem;
import com.example.collections_and_maps.view_model.models.FragmentData;
import com.example.collections_and_maps.domain.models.ListCreator;
import com.example.collections_and_maps.view_model.models.ValueValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BenchmarkViewModel extends ViewModel implements DefaultList {

    private ExecutorService service;

    private final MutableLiveData<List<ResultItem>> itemsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveTextTV = new MutableLiveData<>();

    private final FragmentData fragmentData;

    public LiveData<List<ResultItem>> getItemsLiveData() {
        return itemsLiveData;
    }

    public LiveData<Integer> getLiveTextTV() {
        return liveTextTV;
    }


    public BenchmarkViewModel(FragmentData fragmentData) {
        this.fragmentData = fragmentData;
    }

    @Override
    public void onCreate(boolean isItemAnimated) {
        itemsLiveData.setValue(new ListCreator(fragmentData, isItemAnimated).itemsList);
    }


    public void startMeasure(@NonNull ValueValidator inputtedValue) {
        final int value = inputtedValue.getValue();

        if (service == null || service.isShutdown()) {
            liveTextTV.setValue(R.string.calcButtonStop);

            onCreate(true);
            final List<ResultItem> items = getItemsLiveData().getValue();
            assert items != null;

            final AtomicInteger counterActiveThreads = new AtomicInteger(items.size());

            service = Executors.newCachedThreadPool();
            for (ResultItem rItem : items) {
                service.submit(() -> {
                    final ResultItem resultItem = new CheckedItem(rItem, value, fragmentData).newResultItem;

                    if (!service.isShutdown()) {
                        int index = items.indexOf(rItem);
                        items.set(index, resultItem);
                        itemsLiveData.postValue(new ArrayList<>(items));

                        if (counterActiveThreads.decrementAndGet() == 0) {
                            service.shutdown();
                            liveTextTV.postValue(R.string.calcButtonStart);
                        }
                    }
                });
            }
        } else {
            service.shutdownNow();
            liveTextTV.setValue(R.string.calcButtonStart);
        }
    }

}

