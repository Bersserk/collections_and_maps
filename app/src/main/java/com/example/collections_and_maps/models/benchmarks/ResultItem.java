package com.example.collections_and_maps.models.benchmarks;

public class ResultItem {
    public final int headerText;
    public final int methodName;
    public final double result;
    public final int isAnimate;

    public ResultItem(int headerText, int methodName, double result, int isAnimate) {
        this.headerText = headerText;
        this.methodName = methodName;
        this.result = result;
        this.isAnimate = isAnimate;
    }
}
