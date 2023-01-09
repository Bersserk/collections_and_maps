package com.example.collections_and_maps.models.benchmarks;

public class ResultItem {
    public final int headerText;
    public final int methodName;
    public final double result;
    public final boolean isHeader;

    public ResultItem(int headerText, int methodName, double result, boolean isHeader) {
        this.headerText = headerText;
        this.methodName = methodName;
        this.result = result;
        this.isHeader = isHeader;
    }
}
