package com.example.collections_and_maps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.StepByStep;

public class ListViewAdapter extends RecyclerView.Adapter <ListViewAdapter.ViewHolder>{

    private String [] listArr;


    public ListViewAdapter(String[] listArr) {
        this.listArr = listArr;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameViewTop;

        ViewHolder(View view) {
            super(view);
            StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

            nameViewTop = view.findViewById(R.id.nameViewTop);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.name_view_top;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdForListItem, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.ViewHolder holder, int position) {

        holder.nameViewTop.setText(listArr[position % listArr.length]);

    }

    @Override
    public int getItemCount() {
        return listArr.length;
    }
}
