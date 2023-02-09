package com.example.collections_and_maps.domain.data;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.view_model.interfases.FragmentDataGetter;

public class DataMap implements FragmentDataGetter {

    @Override
    public final int[] getListHeads() {
        return new int[]{R.string.HashMap, R.string.TreeMap};
    }

    @Override
    public final int[] getListMethods() {
        return new int[]{R.string.add_new, R.string.search_key, R.string.removing};
    }
}
