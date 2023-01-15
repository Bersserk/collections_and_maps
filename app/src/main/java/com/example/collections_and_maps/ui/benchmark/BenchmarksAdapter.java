package com.example.collections_and_maps.ui.benchmark;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.databinding.ItemBenchmarkBinding;
import com.example.collections_and_maps.models.benchmarks.DataTV;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.Objects;

public class BenchmarksAdapter extends ListAdapter<ResultItem, BenchmarksAdapter.BenchmarkViewHolder> {

    public static final DiffUtil.ItemCallback<ResultItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ResultItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return oldItem.headerText == newItem.headerText || oldItem.methodName == newItem.methodName;
                }

                @Override
                public boolean areContentsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return Objects.equals(oldItem.result, newItem.result);
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

        private final ItemBenchmarkBinding binding;

        BenchmarkViewHolder(@NonNull ItemBenchmarkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(@NonNull ResultItem item) {

            toSwitchAnimation(item.hasAnimated());
            if (item.isHeader()){
                binding.nameView.setText(item.getValueTV);
            } else {
                binding.nameView.setText(item.getResult());
            }
//            if (item.isHeader()) {
//                toSwitchAnimation(false);
//                binding.nameView.setText(item.valueTV);
//            } else if (item.isWaitingResult()) {
//                toSwitchAnimation(true);
//            } else if (item.hasResult()) {
//                toSwitchAnimation(false);
//                binding.nameView.setText("sss");
//            }
        }

        private void toSwitchAnimation(boolean switcher){
            final ViewPropertyAnimator animator = binding.progressBar.animate();
            if (switcher){
                animator.setDuration(300).alpha(1.0f);
            } else {
                animator.setDuration(100).alpha(0.0f);
            }
        }
    }
}
