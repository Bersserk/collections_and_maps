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
        if (position == 0) {
            return BenchmarkFragment.newInstance(R.string.Collections);
        } else if (position == 1) {
            return BenchmarkFragment.newInstance(R.string.Maps);
        } else {
            throw new RuntimeException("Unsupported type");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
