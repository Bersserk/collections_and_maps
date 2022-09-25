package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.collections_and_maps.R;

import java.util.ArrayList;

public class CollectionsPagerFragment extends BaseFragment {

    BenchmarksAdapter adapter;

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
//        Resources res = this.requireActivity().getResources();
//        listNamesMainItem = res.getStringArray(R.array.collections);
//        listNamesItem = res.getStringArray(R.array.collections_item);
//        spanCount = listNamesMainItem.length;
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
    public void checkInputCorrectly() {
        super.checkInputCorrectly();

        ArrayList <> resultList  = new ArrayList<>();
        // button was pushed, next we are initialisation all views
//        for (int s = 3; s < resultList.size(); s++) {
//            String nameLine = resultList.get(s).toString();
//            resultList.set(++s, new MyArrayList(k, nameLine).getResult());
//            resultList.set(++s, new MyLinkedList(k, nameLine).getResult());
//            resultList.set(++s, new MyCopyOnWriteArrayList(k, nameLine).getResult());
//        }

        // нам нужно запустить цикл потока, где внутри будут вызываться методы на расчет...
        // ...и выдавая результаты обновлять подаваемый в адаптер список

        JThread t= new JThread("JThread ");
        t.start();
        t.notify();

        try{
            t.join();
        }
        catch(InterruptedException e){

            System.out.printf("%s has been interrupted", t.getName());
        }



        for (int s = 0; s < 7; s++) {
            resultList.add("прив".startNewThread(10));
            resultList.add(startNewThread(1));
            resultList.add(startNewThread(5));
            resultList.add(startNewThread(7));
            resultList.add(startNewThread(20));
            resultList.add(startNewThread(8));
            resultList.add(startNewThread(6));

//            resultList.set(++s, new MyLinkedList(k, nameLine).getResult());
//            resultList.set(++s, new MyCopyOnWriteArrayList(k, nameLine).getResult());
        }

        adapter = new BenchmarksAdapter(this, resultList);
        getRecycler().setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        checkInputCorrectly();
    }

    public static void startNewThread(int sec) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sec * 1000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}

class JThread extends Thread {

    JThread(String name){
        super(name);
    }

    public void run(){

        try{
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }

    }
}
