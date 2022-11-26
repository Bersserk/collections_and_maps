package com.example.collections_and_maps.ui.benchmark;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.List;

public class BenchmarksAdapter extends ListAdapter<ResultItem, BenchmarksAdapter.BenchmarkViewHolder> {

    public static final DiffUtil.ItemCallback<ResultItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ResultItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    System.out.println("areItemsTheSame");
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    System.out.println("areContentsTheSame");
                    return oldItem.getResult() == (newItem.getResult());
                }
            };


    public BenchmarksAdapter() {
        super(DIFF_CALLBACK);
        System.out.println("BenchmarksAdapter");
    }

    // 1
    @NonNull
    @Override
    public BenchmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_benchmark, parent, false);
        return new BenchmarkViewHolder(view);
    }

    // 3
    @Override
    public void onBindViewHolder(BenchmarkViewHolder holder, int position) {
        System.out.println("onBindViewHolder");
        ResultItem resultItem = getItem(position);
        int strSize = holder.itemView.getContext().getResources().getStringArray(resultItem.getCollectionName()).length;
        int temp = strSize+1;

        if (position >= 0 && position < strSize) {
            holder.bindTo(holder.itemView.getContext().getResources().getStringArray(resultItem.getCollectionName())[position]);
        } else if (position == strSize || position == (strSize+=temp) ||
                position == (strSize+=temp) || position == (strSize+=temp) ||
                position == (strSize+=temp) || position == (strSize+=temp) ||
                position == (strSize+=temp)) {
            int t = position % strSize ;
            holder.bindTo(holder.itemView.getContext().getResources().getStringArray(resultItem.getMethodName())[t]);
        } else {
            resultItem.setResult(strSize);
            holder.bindTo(resultItem);
        }
    }

    static class BenchmarkViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final ProgressBar progressBar;
        private final ViewPropertyAnimator animator;

        // 2
        BenchmarkViewHolder(View view) {
            super(view);
            System.out.println("BenchmarkViewHolder");
            nameView = view.findViewById(R.id.nameViewList);
            progressBar = view.findViewById(R.id.progressBar);
            animator = progressBar.animate();
        }

        // 4
        synchronized void bindTo(ResultItem item) {
            System.out.println("bindTo");
            if (item.getResult() == 0) {
                animator.setDuration(300).alpha(1.0f);
            } else {
                String st = String.valueOf(item.getResult());
                nameView.setText(st);
            }
        }

        synchronized void bindTo(String item) {
            System.out.println("bindTo");
            nameView.setText(item);
        }
    }
}
