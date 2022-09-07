package com.example.collections_and_maps.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.ComfortableLogsTV;
import com.example.collections_and_maps.R;

import java.util.List;


public class ListViewAdapter extends ListAdapter<String, ListViewAdapter.ViewHolder> {

    public ListViewAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public void submitList(@Nullable List<String> baseList) {
        super.submitList(baseList);
    }

    //1
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_tv_list, parent, false);

        return new ViewHolder(view);
    }

    //3
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;

        //2
        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.nameViewList);
        }

        //4
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
