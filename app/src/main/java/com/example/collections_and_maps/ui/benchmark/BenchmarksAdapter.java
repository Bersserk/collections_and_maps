package com.example.collections_and_maps.ui.benchmark;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.databinding.ItemBenchmarkBinding;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

public class BenchmarksAdapter extends ListAdapter<ResultItem, BenchmarksAdapter.BenchmarkViewHolder> {

    public static final DiffUtil.ItemCallback<ResultItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ResultItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return oldItem.headerText == newItem.headerText || oldItem.methodName == newItem.methodName;
                }

                @Override
                public boolean areContentsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return oldItem.result == newItem.result && oldItem.progressActivated == newItem.progressActivated;
                }
            };

    public BenchmarksAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public BenchmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final ItemBenchmarkBinding binding = ItemBenchmarkBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new BenchmarkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BenchmarkViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    static class BenchmarkViewHolder extends RecyclerView.ViewHolder {

        private final ViewPropertyAnimator animator;
        private final ItemBenchmarkBinding binding;

        BenchmarkViewHolder(ItemBenchmarkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            animator = binding.progressBar.animate();
        }

        public void bindTo(@NonNull ResultItem item) {

            if (item.result == R.integer.zero && item.progressActivated) {
                animator.setDuration(300).alpha(1.0f);
                binding.nameView.setText("");
            } else {
                animator.setDuration(0).alpha(0.0f);

                if (item.headerText != R.string.empty && item.methodName != R.string.empty && item.result == R.integer.zero) {
                    binding.nameView.setText("");
                } else if (item.headerText != R.string.empty && item.methodName != R.string.empty) {
                    binding.nameView.setText(String.valueOf(item.result));
                } else if (item.headerText != R.string.empty) {
                    binding.nameView.setText(item.headerText);
                } else if (item.methodName != R.string.empty) {
                    binding.nameView.setText(item.methodName);
                }
            }
        }
    }
}
