package com.example.collections_and_maps.ui.benchmark;


import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Item;

import java.util.List;

public class CollectionsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 3;
    }

    @Override
    protected List<Item> createTemplateList() {
        return super.createTemplateList(R.array.collections, R.array.collections_item);
    }
}

