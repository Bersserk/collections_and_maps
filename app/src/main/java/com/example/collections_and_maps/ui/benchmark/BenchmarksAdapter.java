package com.example.collections_and_maps.ui.benchmark;

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
import com.example.collections_and_maps.models.logger.Logger;
import java.util.ArrayList;

public class BenchmarksAdapter extends ListAdapter<String, BenchmarksAdapter.BenchmarkViewHolder> {

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


    public void setList(ArrayList <String> resultList) {
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        submitList(resultList);
    }


    public BenchmarksAdapter() {
        super(DIFF_CALLBACK);
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
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


    class BenchmarkViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final ProgressBar progressBar;

        BenchmarkViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.nameViewList);
            progressBar = view.findViewById(R.id.progressBar);
        }

        void bindTo(String s) {
            nameView.setText(s);
            if (s.isEmpty()){
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }
}
