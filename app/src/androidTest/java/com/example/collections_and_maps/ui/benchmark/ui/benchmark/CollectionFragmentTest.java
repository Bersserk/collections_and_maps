package com.example.collections_and_maps.ui.benchmark.ui.benchmark;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.ui.benchmark.Constant;
import com.example.collections_and_maps.ui.benchmark.Util;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CollectionFragmentTest extends BenchmarksTest {

    @Override
    protected Benchmark getBenchmark() {
        return new CollectionsBenchmark();
    }

    @Test
    public void test_fragmentByDefault() {
        onView(withId(R.id.view_pager2)).perform(ViewActions.swipeRight());
        checkEachItem(500, Constant.EMPTY, Constant.PROGRESS_OFF);
    }

    @Test
    public void test_validation_inputNothing() {
        final String errorMessage = ApplicationProvider.getApplicationContext()
                .getString(R.string.empty_input_value);
        onView(withContentDescription(COLLECTIONS)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText(Constant.EMPTY));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).check(matches(ViewMatchers.hasErrorText(errorMessage)));
    }

    @Test
    public void test_validation_inputZero() {
        final String errorMessage = ApplicationProvider.getApplicationContext()
                .getString(R.string.OverZero);
        onView(withContentDescription(MAPS)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText("0"));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).check(matches(ViewMatchers.hasErrorText(errorMessage)));
    }

    @Test
    public void test_fragment_measureAction() {
        onView(withContentDescription(COLLECTIONS)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText(Constant.INPUT));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();

        checkEachItem(1000, Constant.EMPTY, Constant.PROGRESS_ON);
        checkEachItem(13000, "" + Constant.TIME, Constant.PROGRESS_OFF);
    }

    @Test
    public void test_fragment_interruptMeasureAction() {
        onView(withContentDescription(COLLECTIONS)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText(Constant.INPUT));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();

        checkEachItem(1000, Constant.EMPTY, Constant.PROGRESS_ON);
        Util.delay(2000);
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        checkEachItem(2000, Constant.ANY, Constant.ANY);
    }
}
