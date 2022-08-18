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

import com.example.collections_and_maps.fragments.CollectionsPagerFragment;
import com.example.collections_and_maps.DataView;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.StepByStep;

import java.util.ArrayList;
import java.util.List;

public class DataViewAdapter extends RecyclerView.Adapter<DataViewAdapter.ViewHolder> {

    private int numberItems;

//    private LayoutInflater inflater;
//    private List<DataView> dataViews;
//
//    private ArrayList<String> resultFromArrayList;


    public DataViewAdapter(int numberOfItems) {
        numberItems = numberOfItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final ProgressBar progressBar;
        final TextView nameViewTop, nameView, resultView;

        ViewHolder(View view) {

            super(view);
            StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

            progressBar = view.findViewById(R.id.progressBar);
            nameViewTop = view.findViewById(R.id.nameViewTop);
            nameView = view.findViewById(R.id.nameView);
            resultView = view.findViewById(R.id.resultView);
        }


    }


    @NonNull
    @Override
    public DataViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

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
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        //DataView dataView = dataViews.get(position);

        holder.nameViewTop.setText("holder.nameViewTop.setText");
        holder.nameView.setText("holder.nameView.setText");

        holder.progressBar.setVisibility(ProgressBar.VISIBLE);

        //this.resultFromArrayList = new ArrList().getResultFromArrayList();

       // holder.resultView.setText(resultFromArrayList.get(position));

        //holder.progressBar.setVisibility(ProgressBar.INVISIBLE);


    }

    @Override
    public int getItemCount() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        return numberItems;
    }
}
