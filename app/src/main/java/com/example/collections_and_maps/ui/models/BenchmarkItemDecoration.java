package com.example.collections_and_maps.ui.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BenchmarkItemDecoration extends RecyclerView.ItemDecoration {

    private final int offset = 4;
    private final Paint paintOne;
    private final Paint paintTwo;

    public BenchmarkItemDecoration() {
        paintOne = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintOne.setColor(Color.GRAY);
        paintOne.setStyle(Paint.Style.STROKE);
        paintOne.setStrokeWidth(14);

        paintTwo = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintTwo.setColor(Color.DKGRAY);
        paintTwo.setStyle(Paint.Style.STROKE);
        paintTwo.setStrokeWidth(7);
    }

    @Override
    public void getItemOffsets(
            @NonNull Rect outRect, @NonNull View view,
            @NonNull RecyclerView parent, @NonNull RecyclerView.State state
    ) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(offset, offset, offset, offset);
    }

    @Override
    public void onDraw(
            @NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state
    ) {
        super.onDraw(c, parent, state);

        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager == null) {
            return;
        }

        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);

            c.drawRect(
                    layoutManager.getDecoratedLeft(child) + offset + 5,
                    layoutManager.getDecoratedTop(child) + offset * 6,
                    layoutManager.getDecoratedRight(child) - offset * 6,
                    layoutManager.getDecoratedBottom(child) - offset - 4,
                    paintOne
            );

            c.drawRect(
                    layoutManager.getDecoratedLeft(child) + offset + 5,
                    layoutManager.getDecoratedTop(child) + offset * 4,
                    layoutManager.getDecoratedRight(child) - offset * 4,
                    layoutManager.getDecoratedBottom(child) - offset - 5,
                    paintTwo
            );
        }
    }
}
