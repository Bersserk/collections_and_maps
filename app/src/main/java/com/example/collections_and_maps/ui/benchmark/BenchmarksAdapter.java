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
                    return oldItem.methodName == newItem.methodName;
                }

                @Override
                public boolean areContentsTheSame(@NonNull ResultItem oldItem, @NonNull ResultItem newItem) {
                    return oldItem.result == newItem.result && oldItem.isAnimate == newItem.isAnimate;
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
            switch (item.isAnimate) {
                case R.string.clear:      // для отображения без анимации бара и чистым текстом, иначе будет дубль холдера
                    nameView.setText(R.string.clear);
                    break;
                case R.string.animate:        // для отображения ячейки без текста но с анимацией бара
                    animator.setDuration(300).alpha(1.0f);
                    nameView.setText(R.string.clear);
                    break;
                case R.string.result:             // для отображения результата и остановки анимации бара
                    nameView.setText(String.valueOf(item.result));
                    animator.setDuration(300).alpha(0.0f);
                    break;
                case R.string.head:             // для отображения ячейки с head текстом и без анимации бара у холдера
                    nameView.setText(item.headerText);
                    break;
                case R.string.method:             // для отображения ячейки с method текстом и без анимации бара у холдера
                    nameView.setText(item.methodName);
                    break;
            }
        }
    }
}
