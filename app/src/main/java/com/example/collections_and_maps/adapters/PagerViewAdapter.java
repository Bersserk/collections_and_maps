package com.example.collections_and_maps.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.collections_and_maps.fragments.CollectionsPagerFragment;
import com.example.collections_and_maps.fragments.MapsPagerFragment;
import com.example.collections_and_maps.StepByStep;

public class PagerViewAdapter extends FragmentStateAdapter {

    public PagerViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        if (position == 0) {
            return new CollectionsPagerFragment();
        } else {
            return new MapsPagerFragment();
        }
    }

    @Override
    public int getItemCount() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        return 2;
    }
}
