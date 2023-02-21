package com.example.collections_and_maps.ui.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
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
    private final Benchmark benchmark;
    private ExecutorService service;

    public BenchmarkViewModel(Benchmark benchmark) {
        this.benchmark = benchmark;
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

    public void onCreate() {
        itemsLiveData.setValue(benchmark.getItemsList(false));
    }

    public void clearLiveShowerMessage() {
        liveShowerMessages.setValue(null);
    }

    public void startMeasure(@NonNull String inputtedValue) {
        if (service == null || service.isShutdown()) {
            final int value = checkValidateValue(inputtedValue);

            if (value < 0) {
                liveShowerMessages.setValue(R.string.empty_input_value);
                return;
            }

            liveTextTV.setValue(R.string.calcButtonStop);
            itemsLiveData.setValue(benchmark.getItemsList(true));
            final List<ResultItem> items = getItemsLiveData().getValue();
            assert items != null;
            final AtomicInteger counterActiveThreads = new AtomicInteger(items.size());

            service = Executors.newCachedThreadPool();
            for (ResultItem rItem : items) {
                service.submit(() -> {

                    if (!rItem.isHeader()) {

                        final ResultItem resultItem = new ResultItem(rItem.headerText, rItem.methodName,
                                benchmark.getMeasureTime(rItem, value), false);

                        if (!service.isShutdown()) {
                            int index = items.indexOf(rItem);
                            items.set(index, resultItem);
                            itemsLiveData.postValue(new ArrayList<>(items));
                        }
                    }

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

    public int getSpan() {
        return benchmark.getSpan();
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
