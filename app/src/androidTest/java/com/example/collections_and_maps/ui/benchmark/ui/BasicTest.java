package com.example.collections_and_maps.ui.benchmark.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.ui.MainActivity;

public class BasicTest {
    protected static final int COLLECTIONS = R.string.Collections;
    protected static final int MAPS = R.string.Maps;
    protected final int[] TABS_NAMES = {COLLECTIONS, MAPS};

    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
}
