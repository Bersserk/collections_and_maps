package com.example.collections_and_maps.ui.benchmark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;


public class ItemsAdapter extends ListAdapter<String, ItemsAdapter.ViewHolder> {

    public static final DiffUtil.ItemCallback<String> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<String>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull String oldUser, @NonNull String newUser
                ) {
                    return oldUser == newUser;
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull String oldUser, @NonNull String newUser
                ) {
                    return oldUser.equals(newUser);
                }
            };

    public ItemsAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_benchmark, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.nameViewList);
        }

        void bindTo(String s) {
            nameView.setText(s);
        }
    }
}
