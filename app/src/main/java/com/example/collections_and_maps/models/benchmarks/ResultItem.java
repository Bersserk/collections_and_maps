package com.example.collections_and_maps.models.benchmarks;

public class ResultItem {
    public final int methodName;
    public final long result;
    public final int headerText;

    public ResultItem(long result) {
        this.methodName = 0;
        this.result = result;
        this.headerText = 0;
    }


    public ResultItem(int headerText, int methodName) {
        this.headerText = headerText;
        this.methodName = methodName;
        result = 0;
    }


}
