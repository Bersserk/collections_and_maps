package com.example.collections_and_maps.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.collections_and_maps.models.logger.Logger;
import com.example.collections_and_maps.ui.benchmark.CollectionsPagerFragment;
import com.example.collections_and_maps.ui.benchmark.MapsPagerFragment;

public class PagerViewAdapter extends FragmentStateAdapter {

    public PagerViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        Logger.mlog(this.getClass(), Thread.currentThread().getStackTrace()[2]);
    }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new CollectionsPagerFragment();
            } else {
                return new MapsPagerFragment();
            }
        }

    @Override
    public int getItemCount() {
        return 2;
    }
}
