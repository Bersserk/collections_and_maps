package com.example.collections_and_maps.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.StepByStep;

import java.util.ArrayList;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private String[] headList;
    private ArrayList list;


    public ListViewAdapter(String[] listArr) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        headList = listArr;
    }

    public ListViewAdapter(ArrayList listArr) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        list = listArr;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;

        ViewHolder(View view) {
            super(view);
            StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

            int layoutID = (list==null ? R.id.nameViewTop: R.id.nameViewList);
            nameView = view.findViewById(layoutID);
//            nameView = view.findViewById(R.id.nameViewTop);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        Context context = parent.getContext();


        int layoutID = (list==null ? R.layout.name_view_top: R.layout.name_view_list);
        int layoutIdForListItem = layoutID;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdForListItem, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.ViewHolder holder, int position) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        if (list==null) {


            holder.nameView.setText(headList[position]);
        } else {
            holder.nameView.setText(list.get(position % list.size()).toString());

        }

    }

    @Override
    public int getItemCount() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        return list==null ? (headList.length) : (list.size());
    }
}
