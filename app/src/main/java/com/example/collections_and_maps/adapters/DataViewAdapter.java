package com.example.collections_and_maps.adapters;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.ComfortableLogsTV;

import java.util.ArrayList;

public class DataViewAdapter extends RecyclerView.Adapter<DataViewAdapter.ViewHolder> {

    private int numberItems;
    private ArrayList list;
    int i = 0;




    public DataViewAdapter(ArrayList list) {
        numberItems = list.size();
        this.list = list;

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;
        private TextView nameView, resultView;

        ViewHolder(View view) {

            super(view);
            ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

            progressBar = view.findViewById(R.id.progressBar);
//            nameView = view.findViewById(R.id.nameView);
            resultView = view.findViewById(R.id.resultView);
        }


    }


    @NonNull
    @Override
    public DataViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.data_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdForListItem, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);



        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DataViewAdapter.ViewHolder holder, int position) {
        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);


//        i = position;
//
//
//        String text = list[position*3 % list.length];


//        holder.nameView.setText(list.get(position).toString());
        holder.nameView.setText("TreeMap");

        holder.progressBar.setVisibility(ProgressBar.VISIBLE);

    }

    @Override
    public int getItemCount() {
        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        return numberItems;
    }
}
