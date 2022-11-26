package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private ExecutorService service;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private EditText inputFiled;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputFiled = view.findViewById(R.id.inputField);
        final Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

        final int spans = getSpanCount();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                spans, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(spans + 1, 1, spans));

        final RecyclerView listRecycler = requireView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(gridLayoutManager);

        adapter.submitList(this.createTemplateList());
        listRecycler.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

        try {
            final int value = Integer.parseInt(inputFiled.getText().toString());
            if (value > 0 && value < 10000001) {
                // передать введеное значение и запустить расчет☺
            } else if (value >= 10000001) {
                Toast.makeText(getContext(), R.string.LimitValue, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), R.string.OverZero, Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            if (inputFiled.length() == 0) {
                Toast.makeText(getContext(), R.string.NeedAnyValue, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), R.string.OnlyNumber, Toast.LENGTH_LONG).show();
                inputFiled.setText("");
            }
            e.printStackTrace();
        }
    }

    protected abstract int getSpanCount();

    protected abstract List<ResultItem> createTemplateList();

    protected List<ResultItem> createTemplateList(int listNamesMainItem, int listNamesItem) {

        final int lengthListMain = getResources().getStringArray(listNamesMainItem).length;
        final int lengthListItem = getResources().getStringArray(listNamesItem).length;
        final int sizeList = lengthListMain + lengthListMain*lengthListItem + lengthListItem;
        final List<ResultItem> templateList = new ArrayList<>(sizeList);

        for (int i = 0; i < sizeList; i++) {
            templateList.add(new ResultItem(listNamesMainItem, listNamesItem));
        }

        return templateList;
    }

    protected void toRandomValue (int since, int till){
        try {
            double d = since + Math.random() * (till-since);
            Thread.sleep ((long) (d));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    // we will need this block later ***
//    public static CollectionsPagerFragment newInstance(String fragmentData) {
//        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
//        Bundle args = new Bundle();
//        args.putString(TYPE_BENCHMARK, fragmentData);
//        fragment.setArguments(args);
//        return fragment;
//    }
}






