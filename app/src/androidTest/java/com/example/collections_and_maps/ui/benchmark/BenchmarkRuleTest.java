package com.example.collections_and_maps.ui.benchmark;

import android.app.Application;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.ui.MainActivity;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
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

}
