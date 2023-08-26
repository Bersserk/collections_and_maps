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
import com.example.collections_and_maps.ui.benchmark.Constant;
import com.example.collections_and_maps.ui.benchmark.Util;
import com.example.collections_and_maps.ui.benchmark.models.AppModuleTest;
import com.example.collections_and_maps.ui.benchmark.ui.BasicTest;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import java.util.List;

public abstract class BenchmarksTest extends BasicTest {

    @BeforeClass
    public static void set() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModuleTest())
                .build();
        App.getInstance().setAppComponent(appComponent);
    }

    protected abstract Benchmark getBenchmark();

    protected void checkEachItem(
            int delayBeforeMillis, String textOfItem, String alphaOfItem
    ) {
        final List<ResultItem> initiatedList = getBenchmark().getItemsList(true);

        ViewInteraction onRecyclerItems = onView(withId(R.id.recyclerLayoutItems));
        Util.delay(delayBeforeMillis);

        final Matcher<View> text;
        final Matcher<View> alpha;
        if (Constant.ANY.equals(textOfItem) && Constant.ANY.equals(alphaOfItem)) {
            text = Matchers.anyOf(withSubstring(Constant.INPUT), withSubstring(Constant.EMPTY));
            alpha = Matchers.anyOf(withAlpha(1F), withAlpha(0F));
        } else {
            text = withSubstring(textOfItem);
            alpha = withAlpha(Float.parseFloat(alphaOfItem));
        }

        for (int i = 0; i < initiatedList.size(); i++) {
            final ResultItem item = initiatedList.get(i);
            if (item.isHeader()) {
                onRecyclerItems.perform(RecyclerViewActions.scrollToPosition(i))
                        .check(matches(Util.atPosition(i, hasDescendant(withText(item.nameForHeader)))));
            } else {
                onRecyclerItems.perform(RecyclerViewActions.scrollToPosition(i))
                        .check(matches(Util.atPosition(i, hasDescendant(text))));
                onRecyclerItems.check(matches(Util.atPosition(i, hasDescendant(alpha))));
            }
        }
    }
}
