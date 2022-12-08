package com.example.collections_and_maps.models.benchmarks;

public class ResultItem {
    public final int methodName;
    public final int headerText;
    public final long result;
    private static final int EMPTY_RESULT = -1;

    public ResultItem(int headerText, int methodName, long desiredResult) {
        this.headerText = headerText;
        this.methodName = methodName;
        if (headerText > 0 || methodName > 0) {
            this.result = EMPTY_RESULT;
        } else {
            this.result = desiredResult;
        }
    }
}
