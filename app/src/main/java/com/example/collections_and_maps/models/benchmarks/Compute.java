package com.example.collections_and_maps.models.benchmarks;

import android.os.Handler;
import android.os.Looper;

import com.example.collections_and_maps.ui.benchmark.BaseFragment;
import com.example.collections_and_maps.ui.benchmark.BenchmarksAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Compute {

    private List<Item> newDataList, clearList;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final BenchmarksAdapter adapter;
    private ExecutorService service;

    private static Access currentKey;

    public Compute(BenchmarksAdapter adapter, BaseFragment baseFragment) {
        this.adapter = adapter;
        clearList = new ArrayList<>(adapter.getCurrentList());
        currentKey = new Access();
    }

    public void toClear() {
        service.shutdownNow();
        currentKey.setNewKey();
        adapter.submitList(clearList);
    }

    public void toSolve(int inputData) {
        currentKey.setNewKey();
        newDataList = new ArrayList<>(clearList);

        if (service == null || service.isShutdown()) {
            service = Executors.newCachedThreadPool();
            beginThread(inputData, new Collections(currentKey));
//            beginThread(inputData, new Maps(currentKey));
        }
    }

    private void beginThread(int inputData, Collections collections) {

        final Collections result = collections;

        for (Item item : newDataList) {
            if (item.getCollectionName() != null) {
                int id = item.getId();
                service.submit(() -> {
                    final String newRes = result.getResult(item.getMethodName());
                    if(result.getKey() == currentKey.getValue()) {
                        handler.post(() -> {
                            newDataList.set(id, new Item(newRes, id));
                            adapter.submitList(newDataList);
                            adapter.notifyItemChanged(id);
                        });
                    }
                });
            }
        }
    }

    private void beginThread(int inputData, Maps maps) {

        final Maps result = maps;

        for (Item item : newDataList) {
            if (item.getCollectionName() != null) {
                int id = item.getId();
                service.submit(() -> {
                    final String newRes = result.getResult(item.getMethodName());
                    if(result.getKey() == currentKey.getValue()) {
                        handler.post(() -> {

                        // to build a logical for Maps

                            newDataList.set(id, new Item(newRes, id));
                            adapter.submitList(newDataList);
                            adapter.notifyItemChanged(id);
                        });
                    }
                });
            }
        }
    }
}
