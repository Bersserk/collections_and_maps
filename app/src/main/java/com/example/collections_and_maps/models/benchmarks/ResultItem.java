package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

public class ResultItem {
    public final int headerText;
    public final int methodName;
    public final int valueTV;
    public final double result;

    public ResultItem(int headerText, int methodName, double result) {
        this.headerText = headerText;
        this.methodName = methodName;
        this.result = result;
        this.valueTV = headerText == R.string.empty ? methodName : headerText;
    }

    public boolean isHeader() {
        return headerText == R.string.empty || methodName == R.string.empty;
    }

}


