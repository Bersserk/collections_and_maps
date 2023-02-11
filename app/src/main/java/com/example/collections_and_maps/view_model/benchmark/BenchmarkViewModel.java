package com.example.collections_and_maps.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.DataFilter;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.view_model.models.ItemCreator;
import com.example.collections_and_maps.view_model.models.ListCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BenchmarkViewModel extends ViewModel implements DefaultList {

    private ExecutorService service;

    private final MutableLiveData<List<ResultItem>> itemsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveTextTV = new MutableLiveData<>();

    private final DataFilter dataFilter;

    public LiveData<List<ResultItem>> getItemsLiveData() {
        return itemsLiveData;
    }

    public LiveData<Integer> getLiveTextTV() {
        return liveTextTV;
    }


    public BenchmarkViewModel(DataFilter dataFilter) {
        this.dataFilter = dataFilter;
    }

    @Override
    public void onCreate(boolean isItemAnimated) {
        itemsLiveData.setValue(new ListCreator(dataFilter, isItemAnimated).itemsList);
    }


    public void startMeasure(@NonNull String inputtedValue) {
        final int value = checkValidateValue(inputtedValue);

        if (value >= 0 && service == null || service.isShutdown()) {
            liveTextTV.setValue(R.string.calcButtonStop);

            onCreate(true);
            final List<ResultItem> items = getItemsLiveData().getValue();

            assert items != null;
            final AtomicInteger counterActiveThreads = new AtomicInteger(items.size());

            service = Executors.newCachedThreadPool();
            for (ResultItem rItem : items) {
                service.submit(() -> {
                    final ResultItem resultItem = new ItemCreator().create(rItem, value, dataFilter);
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


    private int checkValidateValue(String inputtedValue) {
        int value;
        try {
            value = Integer.parseInt(inputtedValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            value = -1;
        }
        return value;
    }

}

