package com.example.collections_and_maps.ui.benchmark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class BenchmarksAdapter extends ListAdapter<String, BenchmarksAdapter.BenchmarkViewHolder> {

    protected String[] listNamesMainItem, listNamesItem;
    protected ArrayList baseList;
    protected int spanCount;

    public static final DiffUtil.ItemCallback<String> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<String>() {
                @Override
                public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public BenchmarksAdapter(@NonNull CollectionsPagerFragment context) {
        super(DIFF_CALLBACK);
        listNamesMainItem = context.getResources().getStringArray(R.array.collections);
        listNamesItem = context.getResources().getStringArray(R.array.collections_item);
        spanCount = listNamesMainItem.length;

        submitList(createClearGrid());
    }

    public BenchmarksAdapter(@NonNull MapsPagerFragment context) {
        super(DIFF_CALLBACK);
        listNamesMainItem = context.getResources().getStringArray(R.array.maps);
        listNamesItem = context.getResources().getStringArray(R.array.maps_item);
        spanCount = listNamesMainItem.length;

        submitList(createClearGrid());
    }

    public BenchmarksAdapter(@NonNull CollectionsPagerFragment context, ArrayList resultList) {
        super(DIFF_CALLBACK);
        listNamesMainItem = context.getResources().getStringArray(R.array.collections);
        listNamesItem = context.getResources().getStringArray(R.array.collections_item);
        spanCount = listNamesMainItem.length;

        baseList = createClearGrid(resultList);
        submitList(baseList);
    }

    public BenchmarksAdapter(@NonNull MapsPagerFragment context, ArrayList resultList) {
        super(DIFF_CALLBACK);
        listNamesMainItem = context.getResources().getStringArray(R.array.maps);
        listNamesItem = context.getResources().getStringArray(R.array.maps_item);
        spanCount = listNamesMainItem.length;

        baseList = createClearGrid(resultList);
        submitList(baseList);
    }

    @NonNull
    @Override
    public BenchmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_benchmark, parent, false);
        return new BenchmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BenchmarkViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }


    public ArrayList createClearGrid() {
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList clearList = new ArrayList<String>();

        for (int y = 0; y < spanCount; y++) {
            clearList.add(listNamesMainItem[y]);
        }

        for (int y = 0; y < listNamesItem.length; y++) {
            clearList.add(listNamesItem[y]);
            for (int i = 0; i < spanCount; i++) {
                clearList.add("...");
            }
        }
        return clearList;
    }

    public ArrayList createClearGrid(ArrayList resultList) {
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        ArrayList <String> al = createClearGrid();

        ArrayList list = new ArrayList<String>();

        int y=0;
        for (String s: al) {
            if (s == "..." && y < resultList.size()) {

                list.add(resultList.get(y));
                y++;
            } else {
                list.add(s);
            }
        }

        return list;
    }


    class BenchmarkViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final ProgressBar progressBar;

        BenchmarkViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.nameViewList);
            progressBar = view.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

        }

        void bindTo(String s) {
            nameView.setText(s);
            if (!s.equals("")){
                progressBar.setVisibility(View.INVISIBLE);
            }
        }

    }

}
