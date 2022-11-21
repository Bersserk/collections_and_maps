package com.example.collections_and_maps.ui.benchmark;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Item;

import java.util.List;

public class MapsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 2;
    }

    @Override
    public List<Item> createTemplateList() {
        return super.createTemplateList(R.array.maps, R.array.maps_item);
    }
}
