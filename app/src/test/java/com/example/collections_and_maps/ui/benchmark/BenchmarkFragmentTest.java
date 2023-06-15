package com.example.collections_and_maps.ui.benchmark;

//import android.test.rule.ActivityTestRule;


import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions.click;
import androidx.test.filters.LargeTest;


import com.example.collections_and_maps.R;
import com.example.collections_and_maps.ui.MainActivity;

import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BenchmarkFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void clickButtonHome(){
        onView(withId(R.id.callMeasure)).perform(click()).check(matches(isDisplayed()));
    }
}