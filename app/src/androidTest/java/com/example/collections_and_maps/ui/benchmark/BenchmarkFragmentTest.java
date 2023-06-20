package com.example.collections_and_maps.ui.benchmark;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
//@LargeTest
public class BenchmarkFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void dataDisplay_afterLaunchApp() {
        activityScenarioRule.getScenario().onActivity(activity -> {
            onView(withId(R.id.tabs)).check(matches(isDisplayed()));
//            onView(withId(R.id.view_pager2)).check(matches(isDisplayed()));
//            onView(withId(R.id.inputField)).check(matches(isDisplayed()));
//            onView(withId(R.id.calcButton)).check(matches(isDisplayed()));
//            onView(withId(R.id.recyclerLayoutItems)).check(matches(isDisplayed()));
        });


    }

//    @Test
//    public void clickButtonHome(){
//        onView(withId(R.id.calcButton)).perform(click()).check(matches(isDisplayed()));
//    }
}