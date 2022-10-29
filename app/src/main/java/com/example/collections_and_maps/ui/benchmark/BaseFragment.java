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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected static final String STATE = "state";

    protected final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private final Handler mHandler = new Handler();

    protected final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    protected EditText collectionSize;
    protected RecyclerView listRecycler;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Logger.log("Super", this.getClass(), Thread.currentThread().getStackTrace()[2]);
        collectionSize = view.findViewById(R.id.collectionSize);
        Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

        listRecycler = getView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(this.manageGridLayout());
    }

    @Override
    public void onClick(View view) {
        getResults(adapter.getCurrentList(), getSizeList());
    }

    protected abstract void getResults(List<String>templateList, int sizeList);

    protected abstract GridLayoutManager manageGridLayout ();

    protected ArrayList createTemplateList(int listNamesMainItem, int listNamesItem) {

        String[] listMain = getResources().getStringArray(listNamesMainItem);
        String[] listItem = getResources().getStringArray(listNamesItem);

        ArrayList templateList = new ArrayList();
        templateList.addAll(Arrays.asList(listMain));

        for (int y = 0; y < listItem.length; y++) {
            templateList.add(listItem[y]);
            for (int i = 0; i < listMain.length; i++) {
                templateList.add("...");
            }
        }
        return templateList;
    }

    public int getSizeList() {
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        int sizeList = 0;
        // get data from EditText
        if (collectionSize.length() > 0 && TextUtils.isDigitsOnly(collectionSize.getText())) {
            try {
                sizeList = Integer.parseInt(collectionSize.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), R.string.CrashText, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(), R.string.ToastsText, Toast.LENGTH_LONG).show();
        }
        return sizeList;
    }

    protected void fillDataRecycler(List resultList) {
        adapter.submitList(resultList);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listRecycler.getAdapter() == null) {
                    listRecycler.setAdapter(adapter);
                } else {
                    listRecycler.getAdapter().notifyDataSetChanged();
                }
            }
        });
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
