package com.example.collections_and_maps.ui.benchmark;

import android.icu.util.LocaleData;
import android.util.Log;
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
import com.example.collections_and_maps.models.benchmarks.Item;
import com.example.collections_and_maps.models.logger.Logger;

import java.util.List;


public class BenchmarksAdapter extends ListAdapter<Item, BenchmarksAdapter.BenchmarkViewHolder> {

    public static final DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
                    Log.i("----", "oldItem: " + oldItem.getResult());
                    Log.i("----", "newItem: " + newItem.getResult());
                    return oldItem.getResult() == newItem.getResult();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
                    Log.i("----", "oldItem2: " + oldItem.getResult());
                    Log.i("----", "newItem2: " + newItem.getResult());
                    return oldItem.getResult().equals(newItem.getResult());
                }
            };

    public BenchmarksAdapter() {
        super(DIFF_CALLBACK);
        Log.i("----", "DIFF_CALLBACK: " + DIFF_CALLBACK);

    }

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

    @Override
    public void submitList(@Nullable List<Item> list) {
        super.submitList(list);
        Log.i("--", "submitList: " + list.get(5).getResult() + " : " + list.get(6).getResult() );
    }

    class BenchmarkViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final ProgressBar progressBar;

        BenchmarkViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.nameViewList);
            progressBar = view.findViewById(R.id.progressBar);
        }

        void bindTo(Item s) {
            Log.i("---", "bindTo: " + s.getResult());
            ViewPropertyAnimator vp = progressBar.animate();
            if (s.getResult().isEmpty()) {
                vp.setDuration(100).alpha(1.0f);
            } else {
                vp.setDuration(1500).alpha(0.0f);
                nameView.setText(s.getResult());
            }
        }
    }
}
