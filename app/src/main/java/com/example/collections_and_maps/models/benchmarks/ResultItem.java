package com.example.collections_and_maps.models.benchmarks;

public class ResultItem {
    public final int methodName;
    public final int headerText;
    public final long result;

    public ResultItem(int headerText, int methodName, long result) {
        this.headerText = headerText;
        this.methodName = methodName;
        if (headerText > 0 || methodName > 0) {
            this.result = -1;
        } else {
            this.result = result;
        }
    }
}
