package com.example.collections_and_maps.models.benchmarks

import com.example.collections_and_maps.R

data class ResultItem(
    val headerText: Int,
    val methodName: Int,
    val timing: Double,
    val progressVisible: Boolean
) {
    @JvmField
    val nameForHeader: Int = if (headerText == R.string.empty) methodName else headerText

    constructor(oldItem: ResultItem, timing: Double) : this(
        oldItem.headerText,
        oldItem.methodName,
        timing,
        false
    )

    fun isHeader(): Boolean = headerText == R.string.empty || methodName == R.string.empty

    fun isResult(): Boolean = timing > EMPTY

    companion object {
        const val EMPTY = -1.0
    }
}