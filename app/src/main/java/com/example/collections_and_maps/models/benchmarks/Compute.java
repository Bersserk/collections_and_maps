package com.example.collections_and_maps.models.benchmarks;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import com.example.collections_and_maps.ui.benchmark.BenchmarksAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Compute {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final BenchmarksAdapter adapter;
    private final Object token = new Object();
    private ExecutorService service;
    private List<ResultItem> newList;


    public Compute(BenchmarksAdapter adapter, long value) {
        this.adapter = adapter;
        calc(value);
    }

    private void calc(long value) {
        service = Executors.newCachedThreadPool();
        newList = new ArrayList<>(adapter.getCurrentList());

        int i = 0;
        for (ResultItem res : newList) {
            if (res.headerText == 0 && res.methodName == 0) {
                addTask(i);
            }
            i++;
        }

        service.shutdownNow();
    }

    private void addTask(int i) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                updateUI(i, new ResultItem(toRandomValue(0, 3)));
            }
        });
    }

    private void updateUI(int i, ResultItem resultItem) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newList.set(i, resultItem);
                adapter.submitList(new ArrayList<>(newList));
            }
        }, token, 1000);
    }

    public void removePreviousTasks(List<ResultItem> templateList) {
        handler.removeCallbacksAndMessages(token);
        service.shutdownNow();
        adapter.submitList(templateList);
    }


    private long toRandomValue(int since, int till) {
        double d = since + Math.random() * (till - since);
        long res = (long) (d * 1000);
        SystemClock.sleep(res);
        return res;
    }

}
