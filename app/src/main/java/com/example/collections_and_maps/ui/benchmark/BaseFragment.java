package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
//    protected static final String COLLECTIONS = "collections";

    public final BenchmarksAdapter adapter = new BenchmarksAdapter();
    public final Handler mHandler = new Handler();

    protected final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    protected EditText collectionSize;
    protected int sizeArray;
    protected RecyclerView listRecycler;

    protected ArrayList resultList;
    protected Item item;
    protected ArrayList list;
    String[] listNamesMainItem;
    String[] listNamesItem;
    ArrayList arrayList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        return inflater.inflate(R.layout.fragment_benchmark, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Logger.log("Super", this.getClass(), Thread.currentThread().getStackTrace()[2]);

        collectionSize = view.findViewById(R.id.collectionSize);
        Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

        listRecycler = getView().findViewById(R.id.recyclerLayoutItems);
        if (listRecycler.getItemDecorationCount() < 1) {
            listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        }
        listRecycler.setHasFixedSize(true);


    }

    public void getResults() {
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);


        // get data from EditText
        if (collectionSize.length() > 0 && TextUtils.isDigitsOnly(collectionSize.getText())) {
            sizeArray = Integer.parseInt(collectionSize.getText().toString());
        } else {
            Toast.makeText(getContext(), R.string.ToastsText, Toast.LENGTH_LONG).show();
        }
        arrayList = createArrayList(sizeArray);
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

    private ArrayList createArrayList(int size) {
        ArrayList list = new ArrayList (size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    public void refreshResults (ArrayList resultList) {
        adapter.setList(resultList);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                listRecycler.setAdapter(adapter);
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



     // we will need this block later ***
//    public static CollectionsPagerFragment newInstance(String param1) {
//        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
//        Bundle args = new Bundle();
//        args.putString(COLLECTIONS, param1);
//        fragment.setArguments(args);
//        return fragment;
//    }
}
