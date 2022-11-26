package com.example.collections_and_maps.ui.benchmark;


import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.List;

public class MapsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 2;
    }

    @Override
    public List<ResultItem> createTemplateList() {
        return super.createTemplateList(R.array.maps, R.array.maps_item);
    }


    public String getResult(int methodName) {
        String result;
        double start = System.nanoTime();
        getChose(methodName);
        result = String.valueOf((System.nanoTime() - start) / 1000000);

        return result;
    }

    void getChose (int methodName){

        switch (methodName) {
            case 1:
                addingNew();
                break;
            case 2:
                searchByKey();
                break;
            case 3:
                removing();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void addingNew() {
//        toRandomValue(fromTime, toTime);
//        hashMap.put(i, i);
    }

    protected void searchByKey() {
//        toRandomValue(fromTime, toTime);
//        hashMap.get(i).toString();
    }

    private void removing() {
//        toRandomValue(fromTime, toTime);
//        hashMap.remove(i);
    }

}
