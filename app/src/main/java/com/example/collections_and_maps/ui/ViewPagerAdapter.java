package com.example.collections_and_maps.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.ui.benchmark.BenchmarkFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        final int fragmentType = position == 0 ? R.string.Collections : R.string.Maps;
        return BenchmarkFragment.newInstance(fragmentType);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
