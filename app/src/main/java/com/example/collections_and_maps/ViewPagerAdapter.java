package com.example.collections_and_maps;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
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
