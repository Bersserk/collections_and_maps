package com.example.collections_and_maps.ui.benchmark;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.ui.MainActivity;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Rule {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
}