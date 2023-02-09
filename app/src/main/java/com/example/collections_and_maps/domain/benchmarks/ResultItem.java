package com.example.collections_and_maps.domain.benchmarks;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.domain.models.Item;

public class ResultItem extends Item {
    public static final double EMPTY = -1.0;
    public final int nameForHeader;

    public ResultItem(int headerText, int methodName, double timing, boolean progressVisible) {
        super(headerText, methodName, timing, progressVisible);
        this.nameForHeader = headerText == R.string.empty ? methodName : headerText;
    }

    public boolean isHeader() {
        return headerText == R.string.empty || methodName == R.string.empty;
    }

    public boolean isResult() {
        return timing > EMPTY;
    }

}
