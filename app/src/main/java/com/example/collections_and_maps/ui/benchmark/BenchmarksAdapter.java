package com.example.collections_and_maps.ui.benchmark;

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
                    return oldItem.result == newItem.result;
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
        holder.bindTo(getItem(position));
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
//        synchronized void bindTo(ResultItem item) {
//            System.out.println("bindTo");
//            if (item.getResult() == 0) {
//                animator.setDuration(300).alpha(1.0f);
//            } else {
//                String st = String.valueOf(item.getResult());
//                nameView.setText(st);
//            }
//        }

        synchronized void bindTo(ResultItem item) {
            System.out.println("bindTo");
            if (item.result != 0) {
                // print result
            } else if (item.methodName != 0) {
                nameView.setText(item.methodName);
            } else if (item.headerText != 0) {
                nameView.setText(item.headerText);
            }

        }
    }
}
