package com.example.collections_and_maps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;

import java.util.ArrayList;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private String[] headList;
    private ArrayList list;

    public ListViewAdapter(String[] listArr) {
        headList = listArr;
    }

    public ListViewAdapter(ArrayList listArr) {
        list = listArr;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;

        ViewHolder(View view) {
            super(view);

            int layoutID = (list == null ? R.id.nameViewTop : R.id.nameViewList);
            nameView = view.findViewById(layoutID);
        }

        void bind(String s) {
            nameView.setText(s);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutID = (list == null ? R.layout.name_view_top : R.layout.name_view_list);
        int layoutIdForListItem = layoutID;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdForListItem, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.ViewHolder holder, int position) {
        if (list == null) {
            holder.bind(headList[position]);
        } else {
            holder.bind(list.get(position % list.size()).toString());
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? (headList.length) : (list.size());
    }
}
