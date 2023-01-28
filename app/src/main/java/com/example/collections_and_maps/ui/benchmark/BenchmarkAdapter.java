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
                    return oldItem.isHeader() == newItem.isHeader();
                }

                @Override
                public boolean areContentsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return !newItem.progressVisible && oldItem.timing == newItem.timing;
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
        private static final float ON = 1.0f;
        private static final float OFF = 0.0f;
        private final ItemBenchmarkBinding binding;

        BenchmarkViewHolder(@NonNull ItemBenchmarkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(@NonNull ResultItem item) {
            ViewPropertyAnimator anim = binding.progressBar.animate();

            if ((binding.progressBar.getAlpha() == OFF && item.progressVisible)) {
                anim.setDuration(400).alpha(ON).start();
            } else if (!item.progressVisible) {
                anim.setDuration(300).alpha(OFF).start();
            }

            setDisplayItemData(item);
        }

        public void setDisplayItemData(@NonNull ResultItem item) {
            if (item.isHeader()) {
                binding.nameView.setText(item.nameForHeader);
            } else if (item.isResult()) {
                binding.nameView.setText(itemView.getContext().
                        getString(R.string.timing, String.valueOf(item.timing)));
            } else {
                binding.nameView.setText("");
            }
        }
    }
}
