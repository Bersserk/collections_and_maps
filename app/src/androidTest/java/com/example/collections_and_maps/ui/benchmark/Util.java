package com.example.collections_and_maps.ui.benchmark;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import io.reactivex.rxjava3.annotations.NonNull;

public class Util {
    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                if (viewHolder != null) {
                    return itemMatcher.matches(viewHolder.itemView);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("at position: " + position);
                itemMatcher.describeTo(description);
            }
        };
    }
}
