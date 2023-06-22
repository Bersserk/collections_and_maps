package com.example.collections_and_maps.ui.benchmark.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.ui.MainActivity;
import com.example.collections_and_maps.ui.benchmark.Rule;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
//@LargeTest
public class BenchmarkFragmentTest extends Rule {

    @Test
    public void test_tabs_onSwipe() {
        // Swipe to right
        onView(withId(R.id.view_pager2)).perform(ViewActions.swipeRight());
        // Check name after swipe
        onView(withContentDescription(R.string.Maps)).check(ViewAssertions.matches(isDisplayed()));

        // Swipe to left
        onView(withId(R.id.view_pager2)).perform(ViewActions.swipeLeft());
        // Check name after swipe
        onView(withContentDescription(R.string.Collections)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void test_tabs_onClick() {
        onView(withContentDescription(R.string.Collections))
                .perform(ViewActions.click())
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withContentDescription(R.string.Maps))
                .perform(ViewActions.click())
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void test_fragment_isVisibility() {
        // Ожидаем, что фрагмент отображается
        onView(withId(R.id.fragment_container))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}