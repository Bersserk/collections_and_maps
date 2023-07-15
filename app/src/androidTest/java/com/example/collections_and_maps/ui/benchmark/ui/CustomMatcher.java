package com.example.collections_and_maps.ui.benchmark.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import com.example.collections_and_maps.models.benchmarks.ResultItem;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomMatcher {

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> matcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
//                    description.appendText("at position: " + position);
                matcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                return viewHolder != null && matcher.matches(viewHolder.itemView);
            }
        };
    }
}
