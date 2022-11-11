package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {




    protected static final String TYPE_BENCHMARK = "type";

//    private final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    protected EditText collectionSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        collectionSize = view.findViewById(R.id.collectionSize);
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
    }

    @Override
    public void onClick(View view) {
        int sizeList = getSizeList();

//        for (int i = 4; i < 15; i++) {
//            if (i != 7 && i != 11) {
                new Compute(adapter, sizeList).beginThread();
//            }
//        }
    }


    protected abstract int getSpanCount();

//    protected abstract List<String> getResults(List<String> templateList, int sizeList);

    protected abstract List<Item> createTemplateList();

    protected List<Item> createTemplateList(int listNamesMainItem, int listNamesItem) {

        final String[] listMain = getResources().getStringArray(listNamesMainItem);
        final String[] listItem = getResources().getStringArray(listNamesItem);

        final List <Item> templateList = new ArrayList<>();
        for (String s: listMain) {
            templateList.add(new Item(s));
        }

        for (String s : listItem) {
            templateList.add(new Item(s));
            for (int i = 0; i < listMain.length; i++) {
                templateList.add(new Item(listMain[i], s));
            }
        }
        return templateList;
    }

    public int getSizeList() {
        int size;
        try {
            size = Integer.parseInt(collectionSize.getText().toString());
            if (size > 0) {
                return size;
            } else {
                Toast.makeText(getContext(), R.string.OverZero, Toast.LENGTH_LONG).show();
                return 0;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), R.string.ToastsText, Toast.LENGTH_LONG).show();
            return 0;
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






