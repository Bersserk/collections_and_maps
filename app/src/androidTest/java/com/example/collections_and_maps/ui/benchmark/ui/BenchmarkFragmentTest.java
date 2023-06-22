package com.example.collections_and_maps.ui.benchmark.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.R;
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

    @Test
    public void test_tabs_onSwipe() {
        // Swipe to right
        onView(withId(R.id.view_pager2)).perform(ViewActions.swipeRight());
        // Check name after swipe
        onView(withContentDescription(R.string.Maps)).check(matches(isDisplayed()));

        // Swipe to left
        onView(withId(R.id.view_pager2)).perform(ViewActions.swipeLeft());
        // Check name after swipe
        onView(withContentDescription(R.string.Collections)).check(matches(isDisplayed()));
    }

    @Test
    public void test_tabs_onClick() {
        onView(withContentDescription(R.string.Collections))
                .perform(ViewActions.click())
                .check(matches(isDisplayed()));
        onView(withContentDescription(R.string.Maps))
                .perform(ViewActions.click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void test_fragment_isVisibility() {

        List<ResultItem> expectedList = new MapsBenchmark().getItemsList(false);
        int expectedItemCount = expectedList.size();

        onView(withId(R.id.recyclerLayoutItems))
                .check(matches(hasMinimumChildCount(1)));

//        int i = 0;

//        int hashMap = R.string.HashMap;
//        int treeMap = R.string.TreeMap;
//        int adding_new = R.string.add_new;
//        int search_by_key = R.string.search_key;
//        int removing = R.string.removing;
//        int empty = R.string.empty;
//        int str0 = expectedList.get(0).nameForHeader;
//        int str1 = expectedList.get(1).nameForHeader;
//        int str2 = expectedList.get(2).nameForHeader;
//        int str3 = expectedList.get(3).isHeader()?expectedList.get(3).nameForHeader:Integer.parseInt("");
//        double str4 = expectedList.get(4).timing;
//        int str5 = expectedList.get(5).nameForHeader;
//        double str6 = expectedList.get(6).timing;
//        double str7 = expectedList.get(7).timing;
//        int str8 = expectedList.get(8).nameForHeader;
//        double str9 = expectedList.get(9).timing;
        double str10 = expectedList.get(10).timing;

        for (ResultItem item : expectedList) {
            int index = 0;
            if (item.isHeader()) {
                onView(withId(R.id.recyclerLayoutItems))
                        .perform(RecyclerViewActions.scrollToPosition(++index))
                        .check(matches(hasDescendant(withText(item.nameForHeader))));
            } else {
                onView(withId(R.id.recyclerLayoutItems))
                        .perform(RecyclerViewActions.scrollToPosition(index++))
                        .check(matches(hasDescendant(withText(""))));
            }
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

//    @Test
//    public void test_

}