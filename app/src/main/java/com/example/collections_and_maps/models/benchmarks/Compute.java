package com.example.collections_and_maps.models.benchmarks;

import android.os.Handler;
import android.os.Looper;

import com.example.collections_and_maps.ui.benchmark.BenchmarksAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Compute {

    private final BenchmarksAdapter adapter;
    private final ExecutorService service;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private List <ResultItem> newList;

    public Compute(BenchmarksAdapter adapter, long value, List<ResultItem> templateList) {
        this.adapter = adapter;
        service = Executors.newCachedThreadPool();
        calc(value, templateList);
        service.shutdown();
    }

    private void calc(long value, List<ResultItem> templateList) {

        newList = templateList;

        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.submitList(new ArrayList<>(newList));
            }
        });


        int i = 0;
        for (ResultItem res: newList) {
            if (res.headerText == 0 && res.methodName == 0){
                addTask(i);
            }
            i++;
        }

    }

    private void addTask(int i) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                toRandomValue(0, 10);
                ResultItem resultItem = new ResultItem((long) (1.0 +(Math.random() * 100)));
//                newList.set(i, resultItem);
                updateUI(i, resultItem);
            }
        });
    }

    private void updateUI(int i, ResultItem resultItem){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newList.set(i, resultItem);
                adapter.submitList(new ArrayList<>(newList));
            }
        }, 1000);
    }


    private void toRandomValue(int since, int till) {
        System.out.println("in - toRandomValue");

        try {
            double d = since + Math.random() * (till - since);
            Thread.sleep((long) (d * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("out - toRandomValue");

    }
}
