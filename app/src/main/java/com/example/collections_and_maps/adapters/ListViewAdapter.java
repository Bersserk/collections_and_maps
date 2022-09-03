package com.example.collections_and_maps.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.ComfortableLogsTV;
import com.example.collections_and_maps.R;

import java.util.ArrayList;
import java.util.List;


public class ListViewAdapter extends ListAdapter<String, ListViewAdapter.ViewHolder> {
    public ListViewAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_tv_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    @Override
    public void submitList(@Nullable List<String> list) {
        super.submitList(list);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;
        private ProgressBar progressBar;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.nameViewList);
            progressBar = view.findViewById(R.id.progressBar);
//            progressBar.setActivated(false);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }

        void bindTo(String s) {
            nameView.setText(s);
        }
    }

    public static final DiffUtil.ItemCallback<String> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<String>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull String oldUser, @NonNull String newUser) {
                    return oldUser == newUser;
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull String oldUser, @NonNull String newUser) {
                    return oldUser.equals(newUser);
                }
            };
}
