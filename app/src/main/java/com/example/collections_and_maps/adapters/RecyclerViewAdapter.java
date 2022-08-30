package com.example.collections_and_maps.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList list;

    public RecyclerViewAdapter(ArrayList listArr) {
        list = listArr;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.nameViewList);
        }

        void bind(String s) {
            nameView.setText(s);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.name_view_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
            holder.bind(list.get(position % list.size()).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
