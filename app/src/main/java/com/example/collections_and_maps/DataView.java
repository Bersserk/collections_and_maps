package com.example.collections_and_maps;

import android.widget.ProgressBar;
import android.widget.TextView;


public class DataView {

    private String nameViewTop = "";
    private String nameView = "";
    private long size = 0;
    private int number = 0;
    private int result = 0;


    public DataView(String nameViewTop, String nameView, long size, int number){
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        this.nameViewTop = nameViewTop;
        this.nameView = nameView;
        this.size = size;
        this.number = number;
    }


    public String getNameTop() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        return nameViewTop;
    }

    public String getName() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        return nameView;
    }

    public String getResult() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        return String.valueOf(size+number);
    }




}
