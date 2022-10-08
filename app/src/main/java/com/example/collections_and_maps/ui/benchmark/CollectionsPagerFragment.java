package com.example.collections_and_maps.ui.benchmark;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.MyArrayList;
import com.example.collections_and_maps.models.benchmarks.MyCopyOnWriteArrayList;
import com.example.collections_and_maps.models.benchmarks.MyLinkedList;
import com.example.collections_and_maps.models.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsPagerFragment extends BaseFragment {
    private ArrayList resultList;
    private Item item;
    private ArrayList list;
    String[] listNamesMainItem;
    String[] listNamesItem;


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
        listNamesMainItem = getResources().getStringArray(R.array.collections);
        listNamesItem = getResources().getStringArray(R.array.collections_item);

        createClearGrid();

    }

    public void createClearGrid() {
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);



        list = new ArrayList<>();
        item = new Item();
        item.setS("...");

        list.addAll(Arrays.asList(listNamesMainItem));

        for (int y = 0; y < listNamesItem.length; y++) {
            list.add(listNamesItem[y]);
            for (int i = 0; i < listNamesMainItem.length; i++) {
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

        refreshResults(resultList);
    }

    @Override
    public void onClick(View view) {
        getResults();
    }

    private ArrayList createArrayList(int size) {
        ArrayList list = new ArrayList (size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Override
    public void getResults() {
        super.getResults();

        ArrayList arrayList = createArrayList(sizeArray);
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(arrayList);
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(arrayList);

        for (int i=0, y=0; i< list.size(); i++) {
            if (list.get(i).equals(item)){
                beginNewThread(i, arrayList, resultList, y);
                beginNewThread(++i, linkedList, resultList, y);
                beginNewThread(++i, copyOnWriteArrayList, resultList, y);
                y++;
            }
        }
    }


    // this is work's method
    public void beginNewThread(int i, ArrayList listSize, ArrayList resultList, int y) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                resultList.set(i, new MyArrayList(listSize ,y).getResult());
                refreshResults(resultList);
            }
        });
        t.start();
    }

    public void beginNewThread(int i, LinkedList listSize, ArrayList resultList, int y) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                resultList.set(i, new MyLinkedList(listSize ,y).getResult());
                refreshResults(resultList);
            }
        });
        t.start();
    }

    public void beginNewThread(int i, CopyOnWriteArrayList listSize, ArrayList resultList, int y) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                resultList.set(i, new MyCopyOnWriteArrayList(listSize ,y).getResult());
                refreshResults(resultList);
            }
        });
        t.start();
    }


    public void refreshResults (ArrayList resultList) {
        adapter.setList(resultList);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
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

