package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

public class ResultItem {
    public final int headerText;
    public final int methodName;
    public final boolean progressVisible;
    public final double timing;
    public final int nameForHeader;

    public ResultItem(int headerText, int methodName, double timing, boolean progressVisible) {
        this.headerText = headerText;
        this.methodName = methodName;
        this.timing = timing;
        this.progressVisible = progressVisible;
        this.nameForHeader = setNameForHeader();
    }

    private int setNameForHeader() {
        return headerText == R.string.empty ? methodName : headerText;
    }

    public boolean isHeader() {
        return headerText == R.string.empty || methodName == R.string.empty;
    }

    public boolean isNoEmptyResult() {
        return timing != R.string.empty;
    }
}
