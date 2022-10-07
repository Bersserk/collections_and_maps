package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.logger.Logger;

import java.util.ArrayList;
import java.util.Random;

public class CollectionsPagerFragment extends BaseFragment {
    private ArrayList resultList;
    private Item item;
    private ArrayList list;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        // making listNamesItem recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                3, LinearLayoutManager.VERTICAL, false);
        //set SpanSizeLookup
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(4, 1, 3));
        getRecycler().setLayoutManager(gridLayoutManager);

        createClearGrid();
        adapter.setList(resultList);
        getRecycler().setAdapter(adapter);
    }

    public void createClearGrid() {
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        String[] listNamesMainItem = getResources().getStringArray(R.array.collections);
        String[] listNamesItem = getResources().getStringArray(R.array.collections_item);

        list = new ArrayList<>();
        item = new Item();
        item.setS("...");

        for (int y = 0; y < 3; y++) {
            list.add(listNamesMainItem[y]);
        }

        for (int y = 0; y < listNamesItem.length; y++) {
            list.add(listNamesItem[y]);
            for (int i = 0; i < 3; i++) {
                list.add(item);
            }
        }

        resultList = new ArrayList();
        for (Object s: list) {
            if (s.equals(item)){
                resultList.add(item.getS());
            } else {
                resultList.add(s);
            }
        }
    }

    @Override
    public void onClick(View view) {
        getResults();
    }

    @Override
    public void getResults() {
        super.getResults();

        for (int i=0; i<list.size(); i++) {
            if (list.get(i).equals(item)){
                beginNewThread(i, new Random().nextInt(10000), resultList);
            }
        }




//        ArrayList<String> resultList = new ArrayList<>();

//        for (int s = 0; s < 21; s++) {
//            resultList.add("");
//        }
//
//        for (int i = 0; i < 21; i++) {
//            // this is test line for view with timeout
//            beginNewThread(i, new Random().nextInt(10000), resultList);
//            // this is work line
////            beginNewThread(i, sizeArray, resultList);
//        }
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

                    item.setS(String.valueOf(i1));
                    resultList.set (i, item.getS());
                    refreshResults(resultList);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void refreshResults (ArrayList resultList) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapter.setList(resultList);
                getRecycler().setAdapter(adapter);
            }
        });
    }

    class Item {
        private String s;

        public void setS(String s) {
            this.s = s;
        }

        public String getS() {
            return s;
        }
    }


}

