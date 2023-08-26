package com.example.collections_and_maps.ui.benchmark.ui.benchmark;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withAlpha;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.example.collections_and_maps.App;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.AppComponent;
import com.example.collections_and_maps.models.DaggerAppComponent;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.benchmark.models.AppModuleTest;
import com.example.collections_and_maps.ui.benchmark.ui.AtPositionMatcher;
import com.example.collections_and_maps.ui.benchmark.ui.BasicTest;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import java.util.List;

public abstract class BenchmarksTest extends BasicTest {

    protected static final String INPUTTED_VALUE = "1000.0";
    protected static final String PROGRESS_ON = "1.0f";
    protected static final String PROGRESS_OFF = "0.0f";
    protected static final String EMPTY = "";
    protected static final String ANY = "any";

    @BeforeClass
    public static void set() {
        AppComponent appComponent =
                DaggerAppComponent.builder().appModule(new AppModuleTest()).build();
        App.getInstance().setAppComponent(appComponent);
    }

    protected abstract Benchmark getBenchmark();

    protected void checkEachItem(
            int delayBeforeMillis, String textOfItem, String alphaOfItem
    ) {
        final List<ResultItem> initiatedList = getBenchmark().getItemsList(true);

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
