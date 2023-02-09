package com.example.collections_and_maps.domain.data;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.view_model.interfases.FragmentDataGetter;

public class DataCollection implements FragmentDataGetter {

    @Override
    public final int[] getListHeads() {
        return new int[]{R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite};
    }

    @Override
    public final int[] getListMethods() {
        return new int[]{R.string.add_begin, R.string.add_middle,
                R.string.add_end, R.string.search_value, R.string.remove_begin,
                R.string.remove_middle, R.string.remove_end};
    }
}
