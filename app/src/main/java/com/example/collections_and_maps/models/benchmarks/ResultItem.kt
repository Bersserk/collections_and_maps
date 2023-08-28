package com.example.collections_and_maps.models.benchmarks

import com.example.collections_and_maps.R

class ResultItem(
    val headerText: Int,
    val methodName: Int,
    val timing: Double,
    val progressVisible: Boolean
) {
    @JvmField
    val nameForHeader: Int

    init {
        nameForHeader = if (headerText == R.string.empty) methodName else headerText
    }

    fun copy(oldItem: ResultItem, timing: Double): ResultItem {
        return ResultItem(oldItem.headerText, oldItem.methodName, timing, false)
    }

    fun isHeader(): Boolean {
        return headerText == R.string.empty || methodName == R.string.empty
    }

    fun isResult(): Boolean {
        return timing > EMPTY
    }

    companion object {
        const val EMPTY = -1.0
    }
}