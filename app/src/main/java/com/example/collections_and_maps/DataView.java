package com.example.collections_and_maps;

import android.widget.ProgressBar;
import android.widget.TextView;


public class DataView {

    CalculationFragment calculationFragment;
    private String name;
    private String result;

    public DataView(){
        calculationFragment = new CalculationFragment();


        name = calculationFragment.getUpdateBox();
        result = "sdcdr";
    }


    public DataView(String nameView, String resultView){

        name = nameView;
        result = resultView;

    }



    public String getName() {
        return name;
    }

    public String getResult() {

        return result;
    }


}
