package com.example.collections_and_maps.ui.benchmark;

import android.view.LayoutInflater;
import android.view.ViewGroup;

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
                    return oldItem.headerText == newItem.headerText && oldItem.methodName == newItem.methodName;
                }

                @Override
                public boolean areContentsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return oldItem.resultItemTV != newItem.resultItemTV && (oldItem.itemAnimated == newItem.itemAnimated);
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
            toSwitchAnimation(item.itemAnimated);
            setDataForTV(item);
        }

        private void toSwitchAnimation(boolean switcher) {
            if (switcher) {
                binding.progressBar.animate().
                setDuration(300).alpha(1.0f);
            } else {
                binding.progressBar.setAlpha(0.0f);
            }
        }

        public void setDataForTV(@NonNull ResultItem item) {
            if (item.isHeader()){
                binding.nameView.setText(item.getNameForHeader());
            } else if (!(item.resultItemTV == R.string.empty)) {
                binding.nameView.setText(String.format("%s ms", item.resultItemTV));
            }
        }

    }
}
