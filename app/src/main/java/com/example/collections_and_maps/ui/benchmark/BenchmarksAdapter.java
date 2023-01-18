package com.example.collections_and_maps.ui.benchmark;

import android.content.res.Resources;
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
            toSwitchAnimation(item.progressVisible);
            setDataForTV(item);
        }

        private void toSwitchAnimation(boolean switcher) {
            if (switcher) {
                binding.progressBar.animate().setDuration(300).alpha(1.0f);
            } else {
                binding.progressBar.setAlpha(0.0f);
            }
        }

        public void setDataForTV(@NonNull ResultItem item) {
            if (item.isHeader()) {
                binding.nameView.setText(item.getNameForHeader());
            } else if (!(item.timing == R.string.empty)) {

                String strMeatFormat = Resources.getSystem().getString(R.string.tim);
                String strMeatMsg = String.format(strMeatFormat, item.timing);

//            String s = String.((R.string.timing), item.timing);
                binding.nameView.setText(strMeatMsg);
            }
        }
    }
}
