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

    public BenchmarksAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<ResultItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ResultItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return oldItem.getResult().equals(newItem.getResult());
                }
            };


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
        private final ViewPropertyAnimator animator;

        BenchmarkViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.nameViewList);
            progressBar = view.findViewById(R.id.progressBar);
            animator = progressBar.animate();
        }

        synchronized void bindTo(ResultItem item) {
            if (!item.getResult().isEmpty()) {
                nameView.setText(item.getResult());
            } else {
                animator.setDuration(300).alpha(1.0f);

            }
        }
    }
}
