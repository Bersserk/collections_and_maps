package com.example.collections_and_maps.view_model.models;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.domain.data.DataCollection;
import com.example.collections_and_maps.domain.data.DataMap;
import com.example.collections_and_maps.view_model.interfases.FragmentDataGetter;

public class FragmentDataDistributor {

    public final FragmentData fragmentData;

    public FragmentDataDistributor(int namePager) {

        FragmentDataGetter dataFragment = namePager == R.string.Collections
                ? new DataCollection()
                : new DataMap();

        fragmentData = new FragmentData(dataFragment.getListHeads(), dataFragment.getListMethods(), namePager);
    }
}
