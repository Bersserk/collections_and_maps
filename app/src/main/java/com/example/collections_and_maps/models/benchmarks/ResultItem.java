package com.example.collections_and_maps.models.benchmarks;

public class ResultItem {
    public final int methodName;
    public final int headerText;
    public final long result;
    public final boolean isAnimate;

    public ResultItem(int headerText, int methodName, long result, boolean isAnimate) {
        this.methodName = headerText;
        this.headerText = methodName;
        this.result = result;
        this.isAnimate = isAnimate;
    }
}
