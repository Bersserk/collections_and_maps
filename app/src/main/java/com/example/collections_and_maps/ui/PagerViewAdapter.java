package com.example.collections_and_maps.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.DataFilter;
import com.example.collections_and_maps.ui.benchmark.BaseFragment;

public class PagerViewAdapter extends FragmentStateAdapter {

    public PagerViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
            BaseFragment baseFragment = new BaseFragment();
            baseFragment.setDataForFragments(
                    new DataFilter(position == 0 ? R.string.Collections : R.string.Maps));
            return baseFragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
