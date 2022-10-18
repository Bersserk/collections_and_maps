package com.example.collections_and_maps.ui.benchmark;

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
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
public class CollectionsPagerFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        // making listNamesItem recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                3, LinearLayoutManager.VERTICAL, false);
        //set SpanSizeLookup
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(4, 1, 3));
        listRecycler.setLayoutManager(gridLayoutManager);
        listNamesMainItem = getResources().getStringArray(R.array.collections);
        listNamesItem = getResources().getStringArray(R.array.collections_item);

        createClearGrid();
    }

    @Override
    public void onClick(View view) {
        getResults();
    }

    @Override
    public void getResults() {
        super.getResults();

        LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>(arrayList);

        for (int i=0, y=0; i< list.size(); i++) {
            if (list.get(i).equals(item)){
                beginNewThread(i, arrayList, resultList, y);
                beginNewThread(++i, linkedList, resultList, y);
                beginNewThread(++i, copyOnWriteArrayList, resultList, y);
                y++;
            }
        }
    }

    public void beginNewThread(int i, ArrayList <Integer> listSize, ArrayList resultList, int y) {
        Runnable task =new Runnable(){
            public void run(){
                resultList.set(i, "");
                resultList.set(i, new MyArrayList(listSize ,y).getResult());
                refreshResults(resultList);
            }
        };
        cachedThreadPool.execute(task);
    }

    public void beginNewThread(int i, LinkedList<Integer> listSize, ArrayList<String> resultList, int y) {
        Runnable task =new Runnable(){
            public void run(){
                resultList.set(i, "");
                resultList.set(i, new MyLinkedList(listSize ,y).getResult());
                refreshResults(resultList);
            }
        };
        cachedThreadPool.execute(task);
    }

    public void beginNewThread(int i, CopyOnWriteArrayList<Integer> listSize, ArrayList resultList, int y) {
        Runnable task =new Runnable(){
            public void run(){
                resultList.set(i, "");
                resultList.set(i, new MyCopyOnWriteArrayList(listSize ,y).getResult());
                refreshResults(resultList);
            }
        };
        cachedThreadPool.execute(task);
    }
}

