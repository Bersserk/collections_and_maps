package com.example.collections_and_maps.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.DataFilter;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.benchmark.BenchmarksAdapter;
import com.example.collections_and_maps.view_model.models.ItemCreator;
import com.example.collections_and_maps.view_model.models.ListCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FragmentViewModel extends ViewModel {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private ExecutorService service;
    private final MutableLiveData<List<ResultItem>> liveResultItem = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveTextTV = new MutableLiveData<>();
    private final DataFilter dataFilter;

    public LiveData<List<ResultItem>> getLiveResultItem() {
        return liveResultItem;
    }

    public FragmentViewModel(DataFilter dataFilter) {
        System.out.println("View - FragmentViewModel");
        this.dataFilter = dataFilter;
        liveResultItem.setValue(new ListCreator().create(dataFilter, false));
    }



    public void send(BenchmarksAdapter adapter, String inputtedValue) {
        System.out.println("View - onClick");

        if (inputtedValue.equals("")) {
            updateUI(getLiveResultItem().getValue(), adapter);
        } else if (service == null || service.isShutdown()) {
            liveTextTV.setValue(R.string.calcButtonStop);

            // send clear list with animation
            liveResultItem.setValue(new ListCreator().create(dataFilter, true));
            final List<ResultItem> newList = getLiveResultItem().getValue();

            service = Executors.newCachedThreadPool();
            final AtomicInteger counterActiveThreads = new AtomicInteger();
            final int value = checkValidateValue(inputtedValue);

            for (ResultItem rItem : newList) {
                counterActiveThreads.getAndIncrement();
                service.submit(() -> {
                    final ResultItem resultItem = new ItemCreator().create(rItem, value, dataFilter);
                    if (!service.isShutdown()) {
                        int index = newList.indexOf(rItem);
                        newList.set(index, resultItem);
                        liveResultItem.postValue(newList);
                        updateUI(newList, adapter);

                        if (counterActiveThreads.decrementAndGet() == 0) {
                            service.shutdown();
                            liveTextTV.setValue(R.string.calcButtonStart);
                        }
                    }
                });
            }
        } else {
            service.shutdownNow();
            liveTextTV.setValue(R.string.calcButtonStart);

        }
    }


    private void updateUI(List<ResultItem> resultList, BenchmarksAdapter adapter) {
        System.out.println("View - updateUI");
        handler.post(() -> adapter.submitList(new ArrayList<>(resultList)));
    }

    private int checkValidateValue(String inputtedValue) {
        int value;
        try {
            value = Integer.parseInt(inputtedValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            value = 0;
        }
        return value;
    }

}

