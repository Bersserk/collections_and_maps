package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

public class DataFilter {

    private ComputeTime computeTime;
    private int[] listHeadsId;
    private int[] listMethodsId;

    public ComputeTime getComputeTime() {
        return computeTime;
    }

    public int[] getListHeadsId() {
        return listHeadsId;
    }

    public int[] getListMethodsId() {
        return listMethodsId;
    }


    public DataFilter(int namePagerView) {
        switch (namePagerView) {
            case R.string.Collections:
                listHeadsId = collectionsHeads();
                listMethodsId = collectionsMethods();
                computeTime = new CollectionsComputeTime();
                break;
            case R.string.Maps:
                listHeadsId = mapsHeads();
                listMethodsId = mapsMethods();
                computeTime = new MapsComputeTime();
                break;
        }
    }

    private int[] collectionsHeads() {
        return new int[]{R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite};
    }

    private int[] collectionsMethods() {
        return new int[]{R.string.add_begin, R.string.add_middle,
                R.string.add_end, R.string.search_value, R.string.remove_begin,
                R.string.remove_middle, R.string.remove_end};
    }

    private int[] mapsHeads() {
        return new int[]{R.string.HashMap, R.string.TreeMap};
    }

    private int[] mapsMethods() {
        return new int[]{R.string.add_new, R.string.search_key, R.string.removing};
    }


}
