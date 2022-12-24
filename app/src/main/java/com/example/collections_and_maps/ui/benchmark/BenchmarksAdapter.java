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
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return oldItem.result == newItem.result;
                }
            };


    public BenchmarksAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public BenchmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_benchmark, parent, false);
        return new BenchmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BenchmarkViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    static class BenchmarkViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final ViewPropertyAnimator animator;

        BenchmarkViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.nameViewList);
            animator = view.findViewById(R.id.progressBar).animate();
        }

        public void bindTo(@NonNull ResultItem item) {

            if (item.isAnimate && item.result == R.integer.emptyResult) {
                animator.setDuration(300).alpha(1.0f);
            }

            if (item.result != R.integer.emptyResult) {
                if (item.result != R.integer.empty) {
                    nameView.setText(String.valueOf(item.result));
                    animator.setDuration(300).alpha(0.0f);
                } else if (item.methodName != R.integer.empty) {
                    nameView.setText(item.methodName);
                } else if (item.headerText != R.integer.empty) {
                    nameView.setText(item.headerText);
                }
            }
        }
    }
}
