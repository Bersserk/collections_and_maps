package com.example.collections_and_maps.ui.benchmark.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withAlpha;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.benchmark.Rule;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
//@LargeTest
public class BenchmarkFragmentTest extends Rule {

    private final int[] fragments = {R.string.Collections, R.string.Maps};

    @Test
    public void test_tabs_onSwipe() {
        onView(withId(R.id.view_pager2)).perform(ViewActions.swipeLeft());
        onView(withContentDescription(R.string.Maps)).check(matches(isDisplayed()));

        onView(withId(R.id.view_pager2)).perform(ViewActions.swipeRight());
        onView(withContentDescription(R.string.Collections)).check(matches(isDisplayed()));
    }

    @Test
    public void test_tabs_onClick() {
        for (int fragment : fragments) {
            onView(withContentDescription(fragment))
                    .perform(ViewActions.click())
                    .check(matches(isDisplayed()));
        }
    }

    @Test
    public void test_onCollectionFragment_isVisibility() {
        List<ResultItem> collectionList = new CollectionsBenchmark().getItemsList(false);

        int i = 0;
        for (ResultItem item : collectionList) {
            if (item.isHeader()) {
                onView(withId(R.id.recyclerLayoutItems))
                        .perform(RecyclerViewActions.scrollToPosition(i))
                        .check(matches(AtPositionMatcher.atPosition(i, hasDescendant(withText(item.nameForHeader)))));
            }
            i++;
        }
    }

    @Test
    public void test_onMapsFragment_isVisibility() {
        onView(withId(R.id.view_pager2)).perform(ViewActions.swipeLeft());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<ResultItem> mapsList = new MapsBenchmark().getItemsList(false);

        int y = 0;
        for (ResultItem item : mapsList) {
            if (item.isHeader()) {
                onView(withId(R.id.recyclerLayoutItems))
                        .check(matches(AtPositionMatcher.atPosition(y, hasDescendant(withText(item.nameForHeader)))));
            }
            y++;
        }
    }

    @Test
    public void test_editText() {
        ViewInteraction inputField = onView(withId(R.id.inputField));
        inputField.check(matches(Matchers.allOf(isDisplayed(), isFocusable())));
        inputField.perform(ViewActions.typeText("1000"));
    }

    @Test
    public void test_button() {
        ViewInteraction calcButton = onView(withId(R.id.calcButton));
        calcButton.check(matches(Matchers.allOf(isDisplayed(), isClickable())));
        calcButton.perform(ViewActions.click())
                .check(matches(Matchers
                        .anyOf(withText(R.string.calcButtonStart), withText(R.string.calcButtonStop))));
        Espresso.closeSoftKeyboard();
    }

    @Test
    public void test_onCollectionsFragment_isVisibilityProgressBar() {
        List<ResultItem> collectionList = new CollectionsBenchmark().getItemsList(true);

        onView(withContentDescription(R.string.Collections)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText("10000000"));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());

        int i = 0;
        for (ResultItem item : collectionList) {
            if (!item.isHeader()) {
                onView(withId(R.id.recyclerLayoutItems))
                        .perform(RecyclerViewActions.scrollToPosition(i))
                        .check(matches(AtPositionMatcher.atPosition(i, hasDescendant(withAlpha(1.0f)))));
            }
            i++;
        }
    }

    @Test
    public void test_onMapsFragment_isVisibilityProgressBar() {
        List<ResultItem> mapsList = new MapsBenchmark().getItemsList(true);

        onView(withContentDescription(R.string.Maps)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText("10000000"));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());

        int i = 0;
        for (ResultItem item : mapsList) {
            if (!item.isHeader()) {
                onView(withId(R.id.recyclerLayoutItems))
                        .perform(RecyclerViewActions.scrollToPosition(i))
                        .check(matches(AtPositionMatcher.atPosition(i, hasDescendant(withAlpha(1.0f)))));
            }
            i++;
        }
    }

    @Test
    public void test_onCollectionsFragment_measureAction() {
        List<ResultItem> collectionList = new CollectionsBenchmark().getItemsList(true);

        onView(withContentDescription(R.string.Collections)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText("1000"));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = 0;
        for (ResultItem item : collectionList) {
            if (!item.isHeader()) {
                onView(withId(R.id.recyclerLayoutItems))
                        .perform(RecyclerViewActions.scrollToPosition(i))
                        .check(matches(AtPositionMatcher.atPosition(i, hasDescendant(withSubstring("0")))));
            }
            i++;
        }
    }

    @Test
    public void test_onMapsFragment_measureAction() {
        onView(withContentDescription(R.string.Maps)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText("1000"));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<ResultItem> mapsList = new MapsBenchmark().getItemsList(true);

        int i = 0;
        for (ResultItem item : mapsList) {
            if (!item.isHeader()) {
                onView(withId(R.id.recyclerLayoutItems))
                        .perform(RecyclerViewActions.scrollToPosition(i))
                        .check(matches(AtPositionMatcher.atPosition(i, hasDescendant(withSubstring("0")))));
            }
            i++;
        }
    }

    @Test
    public void test_onFragments_inputNothing() {

        String errorMessage = ApplicationProvider.getApplicationContext()
                .getString(R.string.empty_input_value);

        for (int fragment : fragments) {
            onView(withContentDescription(fragment)).perform(ViewActions.click());
            onView(withId(R.id.inputField)).perform(ViewActions.typeText(""));
            onView(withId(R.id.calcButton)).perform(ViewActions.click());
            onView(withId(R.id.inputField)).check(matches(ViewMatchers.hasErrorText(errorMessage)));
        }
    }

}


