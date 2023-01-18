package com.example.collections_and_maps.ui.benchmark;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.databinding.ItemBenchmarkBinding;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

public class BenchmarksAdapter extends ListAdapter<ResultItem, BenchmarksAdapter.BenchmarkViewHolder> {

    public static final DiffUtil.ItemCallback<ResultItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ResultItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return oldItem.isHeader() == newItem.isHeader();
                }

                @Override
                public boolean areContentsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return oldItem.timing != newItem.timing && (oldItem.progressVisible == newItem.progressVisible);
                }
            };

    public BenchmarksAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public BenchmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ItemBenchmarkBinding binding = ItemBenchmarkBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new BenchmarkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BenchmarkViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    static class BenchmarkViewHolder extends RecyclerView.ViewHolder {

        private final ItemBenchmarkBinding binding;

        BenchmarkViewHolder(@NonNull ItemBenchmarkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(@NonNull ResultItem item) {
            if (toSwitchAnimation(item.progressVisible)) {
                setDataForTV(item);
            }
        }

        private boolean toSwitchAnimation(boolean switcher) {
            final ViewPropertyAnimator viewPropertyAnimator = binding.progressBar.animate();
            viewPropertyAnimator.setDuration(300);
            if (switcher) {
                viewPropertyAnimator.alpha(1.0f);
                return false;
            } else {
                viewPropertyAnimator.alpha(0.0f);
                return true;
            }
        }

        public void setDataForTV(@NonNull ResultItem item) {
            if (item.isNoEmptyResult()) {
                binding.nameView.setText(String.format("%s ms", item.timing));
            } else if (item.isHeader()) {
                binding.nameView.setText(item.nameForHeader);
            }
        }
    }
}
