package com.example.collections_and_maps;

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

import java.util.List;

public class DataViewAdapter extends RecyclerView.Adapter<DataViewAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private List<DataView> dataViews;



    DataViewAdapter(CollectionsPagerFragment context, List<DataView> dataViews) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        this.dataViews = dataViews;
        this.inflater = LayoutInflater.from(context.getContext());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ProgressBar progressBar;
        final TextView nameView, resultView;
        ViewHolder(View view){

            super(view);
            StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

            progressBar = view.findViewById(R.id.progressBar);
            nameView = view.findViewById(R.id.nameView);
            resultView = view.findViewById(R.id.resultView);
        }
    }




    @NonNull
    @Override
    public DataViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        View view = inflater.inflate(R.layout.output_data_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DataViewAdapter.ViewHolder holder, int position) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        DataView dataView = dataViews.get(position);

        holder.nameView.setText(dataView.getName());
        holder.progressBar.setVisibility(ProgressBar.VISIBLE);
        holder.resultView.setText(dataView.getResult());
        holder.progressBar.setVisibility(ProgressBar.INVISIBLE);

        // обработка нажатия


    }

    @Override
    public int getItemCount() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        return dataViews.size();
    }
}
