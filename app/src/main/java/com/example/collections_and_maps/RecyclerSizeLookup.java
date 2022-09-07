package com.example.collections_and_maps;


import androidx.recyclerview.widget.GridLayoutManager;

public class RecyclerSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private final int spanPos, spanCnt1, spanCnt2;

    public RecyclerSizeLookup(int spanPos, int spanCnt1, int spanCnt2) {
        super();
        this.spanPos = spanPos;
        this.spanCnt1 = spanCnt1;
        this.spanCnt2 = spanCnt2;
    }

    @Override
    public int getSpanSize(int position) {
        return (position % spanPos == 0 ? spanCnt2 : spanCnt1);
    }
}
