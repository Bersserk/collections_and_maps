package com.example.collections_and_maps;

import android.widget.ProgressBar;
import android.widget.TextView;


public class DataView {

    CalculationFragment calculationFragment;
    private String nameView = "";
    private int size = 0;
    private int number = 0;
    private int result = 0;


    public DataView(String nameView, int size, int number){
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        this.nameView = nameView;
        this.size = size;
        this.number = number;
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
