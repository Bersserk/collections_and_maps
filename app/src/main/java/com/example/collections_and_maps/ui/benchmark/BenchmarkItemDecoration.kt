package com.example.collections_and_maps.ui.benchmark

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class BenchmarkItemDecoration : ItemDecoration() {
    private val offset = 4
    private val paintOne: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintTwo: Paint

    init {
        paintOne.color = Color.GRAY
        paintOne.style = Paint.Style.STROKE
        paintOne.strokeWidth = 14f
        paintTwo = Paint(Paint.ANTI_ALIAS_FLAG)
        paintTwo.color = Color.DKGRAY
        paintTwo.style = Paint.Style.STROKE
        paintTwo.strokeWidth = 7f
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect[offset, offset, offset] = offset
    }

    override fun onDraw(
        c: Canvas, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.onDraw(c, parent, state)
        val layoutManager = parent.layoutManager ?: return
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            c.drawRect(
                (layoutManager.getDecoratedLeft(child) + offset + 5).toFloat(),
                (layoutManager.getDecoratedTop(child) + offset * 6).toFloat(),
                (layoutManager.getDecoratedRight(child) - offset * 6).toFloat(),
                (layoutManager.getDecoratedBottom(child) - offset - 4).toFloat(),
                paintOne
            )
            c.drawRect(
                (layoutManager.getDecoratedLeft(child) + offset + 5).toFloat(),
                (layoutManager.getDecoratedTop(child) + offset * 4).toFloat(),
                (layoutManager.getDecoratedRight(child) - offset * 4).toFloat(),
                (layoutManager.getDecoratedBottom(child) - offset - 5).toFloat(),
                paintTwo
            )
        }
    }
}
