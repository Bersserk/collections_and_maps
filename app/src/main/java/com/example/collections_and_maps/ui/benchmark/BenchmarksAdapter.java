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
        @Nullable @BindView(R.id.nameViewList) TextView nameView;
        @Nullable @BindView(R.id.progressBar) ProgressBar progressBar;

        private final ViewPropertyAnimator animator;

        BenchmarkViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            animator = progressBar.animate();
        }

        public void bindTo(@NonNull ResultItem item) {
            switch (item.isAnimate){
                case R.string.clear:
                    // для отображения без анимации бара и чистым текстом, иначе будет дубль холдера
                    animator.setDuration(300).alpha(0.0f);
                    nameView.setText(R.string.clear);
                    break;
                case R.string.animate:
                    // для отображения ячейки без текста но с анимацией бара
                    animator.setDuration(300).alpha(1.0f);
                    nameView.setText(R.string.clear);
                    break;
                case R.string.result:
                    // для отображения результата и остановки анимации бара
                    animator.setDuration(300).alpha(1.0f);
                    nameView.setText(String.valueOf(item.result));
                    animator.setDuration(300).alpha(0.0f);

                    break;
                case R.string.head:
                    // для отображения ячейки с head текстом и без анимации холдера
                    animator.setDuration(1).alpha(0.0f);
                    nameView.setText(item.headerText);
                    break;
                case R.string.method:
                    // для отображения ячейки с method текстом и без анимации холдера
                    animator.setDuration(1).alpha(0.0f);
                    nameView.setText(item.methodName);
                    break;
            }
        }

//        private void getValue (ResultItem item){
//
//        }


//        public void bindTo(@NonNull ResultItem item) {
////            if (item.result != R.integer.empty) {
////                nameView.setText(String.valueOf(item.result));}
////            nameView.clearAnimation();
//
//            if (item.result == R.string.emptyText){
//                nameView.setText("");
//            }
//
//
//            if (item.isAnimate && item.result == R.integer.emptyResult) {
//                nameView.setText("");
//                animator.setDuration(300).alpha(1.0f);
//            } else {
//                animator.setDuration(300).alpha(0.0f);
//            }
//
//            if (item.result != R.string.emptyText) {
//                if (item.result != R.integer.empty) {
//                    nameView.setText(String.valueOf(item.result));
//                    animator.setDuration(300).alpha(0.0f);
//                } else if (item.methodName != R.integer.empty) {
//                    nameView.setText(item.methodName);
//                } else if (item.headerText != R.integer.empty) {
//                    nameView.setText(item.headerText);
//                }
//            }
//        }
    }
}
