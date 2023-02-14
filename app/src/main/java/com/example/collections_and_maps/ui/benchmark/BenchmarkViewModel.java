package com.example.collections_and_maps.ui.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.ItemCreator;
import com.example.collections_and_maps.models.benchmarks.ListCreator;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BenchmarkViewModel extends ViewModel {

    private final MutableLiveData<List<ResultItem>> itemsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveTextTV = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveShowerMessages = new MutableLiveData<>();
    private final Benchmark fragmentData;
    private ExecutorService service;

    public BenchmarkViewModel(Benchmark fragmentData) {
        this.fragmentData = fragmentData;
        onCreate();
    }

    public LiveData<List<ResultItem>> getItemsLiveData() {
        return itemsLiveData;
    }

    public LiveData<Integer> getLiveTextTV() {
        return liveTextTV;
    }

    public LiveData<Integer> getLiveShowerMessages() {
        return liveShowerMessages;
    }

    private void onCreate() {
        itemsLiveData.setValue(new ListCreator(fragmentData, false).itemsList);
    }

    public void startMeasure(@NonNull String inputtedValue) {
        if (service == null || service.isShutdown()) {
            final int value = checkValidateValue(inputtedValue);
            if (value < 0) {
                liveShowerMessages.setValue(R.string.OtherValue);
                return;
            }

            liveTextTV.setValue(R.string.calcButtonStop);
            itemsLiveData.setValue(new ListCreator(fragmentData, true).itemsList);
            final List<ResultItem> items = getItemsLiveData().getValue();
            final AtomicInteger counterActiveThreads = new AtomicInteger(items.size());

            service = Executors.newCachedThreadPool();
            for (ResultItem rItem : items) {
                service.submit(() -> {
                    final ResultItem resultItem = new ItemCreator().create(rItem, value, fragmentData);
                    int index = items.indexOf(rItem);
                    items.set(index, resultItem);
                    itemsLiveData.postValue(new ArrayList<>(items));

                    if (counterActiveThreads.decrementAndGet() == 0) {
                        service.shutdown();
                        liveTextTV.postValue(R.string.calcButtonStart);
                    }
                });
            }
        } else {
            service.shutdownNow();
            liveTextTV.setValue(R.string.calcButtonStart);
        }
    }


    private int checkValidateValue(String inputtedValue) {
        int value = -1;
        try {
            value = Integer.parseInt(inputtedValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return value;
    }
}
