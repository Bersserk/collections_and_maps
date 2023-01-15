package com.example.collections_and_maps.models.benchmarks;

import android.renderscript.Sampler;

import com.example.collections_and_maps.R;

public class ResultItem{
    public final int headerText;
    public final int methodName;
    public final boolean hasAnimated;
    public String result;
    public int getValueTV;

    public ResultItem(int headerText, int methodName, double result, boolean hasAnimated) {
        this.headerText = headerText;
        this.methodName = methodName;
        this.result = String.valueOf(result);
        this.hasAnimated = hasAnimated;
    }


    public boolean hasAnimated() {
        return hasAnimated;
    }

    public boolean isHeader() {

        if (headerText == R.string.empty){
            getValueTV = methodName;
            return true;
        } else if (methodName == R.string.empty){
            getValueTV = headerText;
            return true;
        } else {

        return false;
        }




    }

    public String getResult() {
        if(hasAnimated){
            return result = "";
        } else {
            return result;
        }
    }
}
