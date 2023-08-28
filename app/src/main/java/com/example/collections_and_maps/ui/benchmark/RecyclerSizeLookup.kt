package com.example.collections_and_maps.ui.benchmark

import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup

class RecyclerSizeLookup(
    private val spanPos: Int,
    private val spanCnt1: Int,
    private val spanCnt2: Int
) : SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        return if (position % spanPos == spanCnt2) spanCnt2 else spanCnt1
    }
}