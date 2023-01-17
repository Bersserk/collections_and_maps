package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

public class ResultItem {
    public final int headerText;
    public final int methodName;
    public final boolean progressVisible;
    public final double timing;

    private int nameForHeader;

    public ResultItem(int headerText, int methodName, double timing, boolean progressVisible) {
        this.headerText = headerText;
        this.methodName = methodName;
        this.timing = timing;
        this.progressVisible = progressVisible;
    }

    public boolean isHeader() {
        if (headerText == R.string.empty) {
            nameForHeader = methodName;
            return true;
        } else if (methodName == R.string.empty) {
            nameForHeader = headerText;
            return true;
        } else {
            return false;
        }
    }

    public int getNameForHeader() {
        return nameForHeader;
    }
}
