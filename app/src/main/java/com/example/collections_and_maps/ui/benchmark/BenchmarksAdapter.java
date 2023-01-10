package com.example.collections_and_maps.ui.benchmark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_benchmark, parent, false);
        return new BenchmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BenchmarkViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    static class BenchmarkViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.nameViewList)
        TextView nameView;
        @Nullable
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        private final ViewPropertyAnimator animator;

        BenchmarkViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            animator = progressBar.animate();
        }

        public void bindTo(@NonNull ResultItem item) {

            if (item.result == R.integer.zero && item.progressActivated) {
                animator.setDuration(300).alpha(1.0f);
                nameView.setText("");
            } else {
                animator.setDuration(0).alpha(0.0f);

                if(item.headerText != R.string.empty && item.methodName != R.string.empty && item.result == R.integer.zero){
                    nameView.setText("");
                } else if(item.headerText != R.string.empty && item.methodName != R.string.empty){
                    nameView.setText(String.valueOf(item.result));
                } else if (item.headerText != R.string.empty){
                    nameView.setText(item.headerText);
                } else if (item.methodName != R.string.empty){
                    nameView.setText(item.methodName);
                }
            }
        }
    }
}
