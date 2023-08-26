package com.example.collections_and_maps.ui.benchmark;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withAlpha;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.app.Application;
import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.collections_and_maps.App;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.AppComponent;
import com.example.collections_and_maps.models.DaggerAppComponent;
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.MainActivity;
import com.example.collections_and_maps.ui.benchmark.models.AppModuleTest;
import com.example.collections_and_maps.ui.benchmark.ui.AtPositionMatcher;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import java.util.List;

public class BenchmarkRuleTest extends Application {

    protected static final int COLLECTIONS = R.string.Collections;
    protected static final int MAPS = R.string.Maps;
    protected static final String INPUTTED_VALUE = "1000.0";
    protected static final String PROGRESS_ON = "1.0f";
    protected static final String PROGRESS_OFF = "0.0f";
    protected static final String EMPTY = "";
    protected static final String ANY = "any";

    protected final int[] TABS_NAMES = {COLLECTIONS, MAPS};

    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @BeforeClass
    public static void set() {
        AppComponent appComponent =
                DaggerAppComponent.builder().appModule(new AppModuleTest()).build();
        App.getInstance().setAppComponent(appComponent);
    }

    protected void checkEachItem(
            int delayBeforeMillis, int list, String textOfItem, String alphaOfItem
    ) {
        final List<ResultItem> initiatedList = (list == COLLECTIONS)
                ? new CollectionsBenchmark().getItemsList(true)
                : new MapsBenchmark().getItemsList(true);

        ViewInteraction onRecyclerItems = onView(withId(R.id.recyclerLayoutItems));
        delay(delayBeforeMillis);

        final Matcher<View> text;
        final Matcher<View> alpha;
        if (ANY.equals(textOfItem) && ANY.equals(alphaOfItem)) {
            text = Matchers.anyOf(withSubstring(INPUTTED_VALUE), withSubstring(EMPTY));
            alpha = Matchers.anyOf(withAlpha(1F), withAlpha(0F));
        } else {
            text = withSubstring(textOfItem);
            alpha = withAlpha(Float.parseFloat(alphaOfItem));
        }

        for (int i = 0; i < initiatedList.size(); i++) {
            final ResultItem item = initiatedList.get(i);
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

    protected void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
