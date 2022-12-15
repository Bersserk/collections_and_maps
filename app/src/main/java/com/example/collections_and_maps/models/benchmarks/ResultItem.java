package com.example.collections_and_maps.models.benchmarks;

public class ResultItem {
    private static final int EMPTY_RESULT = -1;
    public final int methodName;
    public final int headerText;
    /**
     * takes the value "-1" if at least one of the other fields is not 0
     */
    public final long result;

    public ResultItem(int headerText, int methodName) {
        this.headerText = headerText;
        this.methodName = methodName;
        this.result = EMPTY_RESULT;
    }

    public ResultItem() {
        this.methodName = 0;
        this.headerText = 0;
        this.result = 0;
    }

    public ResultItem(long toRandomValue) {
        this.methodName = 0;
        this.headerText = 0;
        this.result = toRandomValue;
    }


}
