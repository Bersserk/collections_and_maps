package com.example.collections_and_maps.ui.benchmark.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.ui.benchmark.BenchmarkRule;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends BenchmarkRule {

    @Test
    public void test_tabs_isDisplayed() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()));
    }

    @Test
    public void test_viewPager2_isDisplayed() {
        onView(withId(R.id.view_pager2)).check(matches(isDisplayed()));
    }

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
}
