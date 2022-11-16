package com.example.collections_and_maps.ui.benchmark;

import android.content.Context;
import android.os.Build;
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
import com.example.collections_and_maps.models.benchmarks.Compute;
import com.example.collections_and_maps.models.benchmarks.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private Handler handler = new Handler(Looper.getMainLooper());
    private InputData inputData;
    Compute compute;

    protected static final String TYPE_BENCHMARK = "type";

    private BenchmarksAdapter adapter = new BenchmarksAdapter();
    protected EditText collectionSize;
    boolean yes = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText collectionSize = view.findViewById(R.id.collectionSize);
        inputData = new InputData(collectionSize, this.getContext());
        Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

        final int spans = getSpanCount();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                spans, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(spans + 1, 1, spans));

        RecyclerView listRecycler = getView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(gridLayoutManager);

        adapter.submitList(this.createTemplateList());
        listRecycler.setAdapter(adapter);
        compute = new Compute(adapter);
    }

    @Override
    public void onClick(View view) {
        if(compute.isFlag()){
            compute.set(false);
            compute.solve(inputData.getInputData());

        } else if (inputData.isNoEqual() || inputData.getInputData() != 0) {
            compute.clear();
            compute.solve(inputData.getInputData());
        } else {
            Toast.makeText(getContext(), R.string.OtherValue, Toast.LENGTH_LONG).show();
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
            for (int i = 0; i < listMain.length; i++) {
                templateList.add(new Item(listMain[i], s, id++));
            }
        }
        return templateList;
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

class InputData {
    public int getInputData() {
        return inputData;
    }

    int inputData;
    EditText fieldInputData;
    Context context;

    public InputData(EditText fieldInputData, Context context) {
        this.fieldInputData = fieldInputData;
        this.context = context;
    }

    public int set() {
        String text = fieldInputData.getText().toString();
        try {
            int newInputData = Integer.parseInt(text);
            if (newInputData > 0) {
                return newInputData;
            } else {
                Toast.makeText(context, R.string.OverZero, Toast.LENGTH_LONG).show();
                return 0;
            }
        } catch (NumberFormatException e) {
            if (text.toCharArray().length > 0) {
                Toast.makeText(context, R.string.ToastsText, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, R.string.NeedAnyValue, Toast.LENGTH_LONG).show();
            }
            return 0;
        }
    }

    public boolean isNoEqual() {
        int temp = set();
        if (inputData != temp) {
            inputData = temp;
            return true;
        } else {
            return false;
        }
    }
}






