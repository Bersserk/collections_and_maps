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
import com.example.collections_and_maps.models.benchmarks.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_LIST_SIZE = 10000001;
    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private ExecutorService service = Executors.newCachedThreadPool();
    private final Handler handler = new Handler(Looper.getMainLooper());

    protected final int tillTime = 7000;
    protected final int sinceTime = 0;
    protected String result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText inputFiled = view.findViewById(R.id.inputField);
        this.setEnterTransition(inputFiled);
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
        final EditText enteredText = (EditText) this.getEnterTransition();
        try {
            final int value = Integer.parseInt(enteredText.getText().toString());
            if (value > 0 && value < MAX_LIST_SIZE) {
                // передать введеное значение и запустить расчет☺
            } else if (value >= MAX_LIST_SIZE) {
                Toast.makeText(getContext(), R.string.LimitValue, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), R.string.OverZero, Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            if (enteredText.length() == 0) {
                Toast.makeText(getContext(), R.string.NeedAnyValue, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), R.string.OnlyNumber, Toast.LENGTH_LONG).show();
                enteredText.setText("");
            }
            e.printStackTrace();
        }
    }

    protected abstract int getSpanCount();

    protected abstract List<Item> createTemplateList();

    protected List<Item> createTemplateList(int listNamesMainItem, int listNamesItem) {
        final String[] listMain = getResources().getStringArray(listNamesMainItem);
        final String[] listItem = getResources().getStringArray(listNamesItem);

        final List<Item> templateList = new ArrayList<>();

        int id = 0;
        for (String s : listMain) {
            templateList.add(new Item(s, id++));
        }

        for (String s : listItem) {
            templateList.add(new Item(s, id++));
            for (String value : listMain) {
                templateList.add(new Item(value, s, id++));
            }
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






