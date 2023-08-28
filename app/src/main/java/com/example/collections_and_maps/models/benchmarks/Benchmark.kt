package com.example.collections_and_maps.models.benchmarks

interface Benchmark {
    fun getMeasureTime(rItem: ResultItem, value: Int): Double
    fun getItemsList(showProgress: Boolean): List<ResultItem>
    fun getSpan(): Int
}
