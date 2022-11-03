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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected static final String TYPE_BENCHMARK = "type";

    protected final BenchmarksAdapter adapter = new BenchmarksAdapter();
    protected EditText collectionSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Log.i("life", "onCreateView");
//        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Log.i("life", "onViewCreated");
//        Logger.log("Super", this.getClass(), Thread.currentThread().getStackTrace()[2]);
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

    protected abstract int getSpanCount();

    @Override
    public void onClick(View view) {
//        Log.i("life", "onClick");

        // по нажатию получаем новый список, далее нужно его передать в адаптер - задача в процессе
        List ls = getResults(adapter.getCurrentList(), getSizeList());
        adapter.submitList(ls);
    }

    protected abstract List getResults(List<String> templateList, int sizeList);

    protected abstract List<String> createTemplateList();

    protected List<String> createTemplateList(int listNamesMainItem, int listNamesItem) {

        String[] listMain = getResources().getStringArray(listNamesMainItem);
        String[] listItem = getResources().getStringArray(listNamesItem);

        List<String> templateList = new ArrayList();
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
        int size = 0;
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
