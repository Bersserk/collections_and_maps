package com.example.collections_and_maps.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.ui.benchmark.FragmentView;

public class PagerViewAdapter extends FragmentStateAdapter {
    private static final int SPAN_COLLECTIONS = 3;
    private static final int SPAN_MAPS = 2;

    public PagerViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public androidx.fragment.app.Fragment createFragment(int position) {
        final int namePagerView = position == 0 ? R.string.Collections : R.string.Maps;
        final int span = position == 0 ? SPAN_COLLECTIONS : SPAN_MAPS;
        return FragmentView.newInstance(namePagerView, span);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
