package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.collections_and_maps.R;
import java.util.ArrayList;
import java.util.Random;

public class CollectionsPagerFragment extends BaseFragment {

    public static CollectionsPagerFragment newInstance(String param1) {
        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
        Bundle args = new Bundle();
        args.putString(COLLECTIONS, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

        // making listNamesItem recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                3, LinearLayoutManager.VERTICAL, false);
        //set SpanSizeLookup
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(4, 1, 3));
        getRecycler().setLayoutManager(gridLayoutManager);

        // createClearGrid
        adapter = new BenchmarksAdapter(this);
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

        for (int s = 0; s < 21; s++) {
            resultList.add("");
        }

        for (int i = 0; i < 21; i++) {
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
        adapter = new BenchmarksAdapter(this, resultList);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                getRecycler().setAdapter(adapter);
            }
        });
    }


}

