package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.collections_and_maps.R;

import java.util.ArrayList;
import java.util.Random;

public class MapsPagerFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // making list recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                2, LinearLayoutManager.VERTICAL, false);
        //set SpanSizeLookup()
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(3, 1, 2));
        getRecycler().setLayoutManager(gridLayoutManager);

//        adapter = new BenchmarksAdapter(this);
        getRecycler().setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        getResults();
    }

    @Override
    public void getResults() {
        super.getResults();

        ArrayList<String> resultList = new ArrayList<>();

        for (int s = 0; s < 6; s++) {
            resultList.add("");
        }

        for (int i = 0; i < 6; i++) {
            // this is test line for view with timeout
            beginNewThread(i, new Random().nextInt(10000), resultList);
            // this is work line
//            beginNewThread(i, sizeArray, resultList);
        }
    }

    // this is work's method
/*    public void beginNewThread(int i, int sizeArray, ArrayList resultList) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                resultList.set(i, new MyArrayList(sizeArray ,i).getResult());
                refreshResults(resultList);
            }
        });
        t.start();
    }
 */


    // this is method for test
    public void beginNewThread (int i, int i1, ArrayList resultList) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(i1);

                    resultList.set (i, "" +i);
                    refreshResults(resultList);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void refreshResults (ArrayList resultList) {
//        adapter = new BenchmarksAdapter(this, resultList);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                getRecycler().setAdapter(adapter);
            }
        });
    }


}
