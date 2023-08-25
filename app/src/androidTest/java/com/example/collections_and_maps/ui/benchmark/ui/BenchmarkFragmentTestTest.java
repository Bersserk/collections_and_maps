package com.example.collections_and_maps.ui.benchmark.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withAlpha;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.App;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.AppComponent;
import com.example.collections_and_maps.models.DaggerAppComponent;
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.benchmark.BenchmarkRuleTest;
import com.example.collections_and_maps.ui.benchmark.models.AppModuleTest;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class BenchmarkFragmentTestTest extends BenchmarkRuleTest {

    @BeforeClass
    public static void set() {
        AppComponent appComponent =
                DaggerAppComponent.builder().appModule(new AppModuleTest()).build();
        App.getInstance().setAppComponent(appComponent);
    }

    @Test
    public void test_onCollectionFragment_byDefault() {
        onView(withId(R.id.view_pager2)).perform(ViewActions.swipeRight());
        checkEachItem(500, COLLECTIONS, "", "0.0f");
    }

    @Test
    public void test_onMapFragment_byDefault() {
        onView(withId(R.id.view_pager2)).perform(ViewActions.swipeLeft());
        checkEachItem(500, MAPS, "", "0.0f");
    }

    @Test
    public void test_onFragments_inputNothing() {
        final String errorMessage = ApplicationProvider.getApplicationContext()
                .getString(R.string.empty_input_value);
        for (int fragment : TABS_NAMES) {
            onView(withContentDescription(fragment)).perform(ViewActions.click());
            onView(withId(R.id.inputField)).perform(ViewActions.typeText(""));
            onView(withId(R.id.calcButton)).perform(ViewActions.click());
            onView(withId(R.id.inputField)).check(matches(ViewMatchers.hasErrorText(errorMessage)));
        }
    }

    @Test
    public void test_onFragments_inputZero() {
        final String errorMessage = ApplicationProvider.getApplicationContext()
                .getString(R.string.OverZero);
        for (int fragment : TABS_NAMES) {
            onView(withContentDescription(fragment)).perform(ViewActions.click());
            onView(withId(R.id.inputField)).perform(ViewActions.typeText("0"));
            onView(withId(R.id.calcButton)).perform(ViewActions.click());
            onView(withId(R.id.inputField)).check(matches(ViewMatchers.hasErrorText(errorMessage)));
        }
    }

    @Test
    public void test_onCollectionFragment_measureAction() {
        onView(withContentDescription(COLLECTIONS)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText("1000"));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();

        checkEachItem(1000, COLLECTIONS, "", "1.0f");
        checkEachItem(20000, COLLECTIONS, "1000.0", "0.0f");
    }

    @Test
    public void test_onMapFragment_measureAction() {
        onView(withContentDescription(MAPS)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText("1000"));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();

        checkEachItem(1000, MAPS, "", "1.0f");
        checkEachItem(4000, MAPS, "1000.0", "0.0f");
    }

    @Test
    public void test_onCollectionFragment_interruptMeasureAction() {
        onView(withContentDescription(COLLECTIONS)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText("1000"));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();

        checkEachItem(1000, COLLECTIONS, "", "1.0f");
        delay(2000);
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        checkEachItem(2000, COLLECTIONS, "any", "any");
    }

    @Test
    public void test_onMapFragment_interruptMeasureAction() {
        onView(withContentDescription(MAPS)).perform(ViewActions.click());
        onView(withId(R.id.inputField)).perform(ViewActions.typeText("1000"));
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();

        checkEachItem(1000, MAPS, "", "1.0f");
        delay(500);
        onView(withId(R.id.calcButton)).perform(ViewActions.click());
        checkEachItem(2000, MAPS, "any", "any");
    }


    private void checkEachItem(
            int delayBeforeMillis, int list, String textOfItem, String alphaOfItem
    ) {
        final List<ResultItem> initedList = (list == COLLECTIONS)
                ? new CollectionsBenchmark().getItemsList(true)
                : new MapsBenchmark().getItemsList(true);

        ViewInteraction onRecyclerItems = onView(withId(R.id.recyclerLayoutItems));
        delay(delayBeforeMillis);

        final Matcher<View> text;
        final Matcher<View> alpha;
        if ("any".equals(textOfItem) && "any".equals(alphaOfItem)) {
            text = Matchers.anyOf(withSubstring("1000.0"), withSubstring(""));
            alpha = Matchers.anyOf(withAlpha(1F), withAlpha(0F));
        } else {
            text = withSubstring(textOfItem);
            alpha = withAlpha(Float.parseFloat(alphaOfItem));
        }

        for (int i = 0; i < initedList.size(); i++) {
            final ResultItem item = initedList.get(i);
            if (item.isHeader()) {
                onRecyclerItems.perform(RecyclerViewActions.scrollToPosition(i))
                        .check(matches(AtPositionMatcher.atPosition(i, hasDescendant(withText(item.nameForHeader)))));
            } else {
                onRecyclerItems.perform(RecyclerViewActions.scrollToPosition(i))
                        .check(matches(AtPositionMatcher.atPosition(i, hasDescendant(text))));
                onRecyclerItems.check(matches(AtPositionMatcher.atPosition(i, hasDescendant(alpha))));
            }
        }
    }

    private void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
