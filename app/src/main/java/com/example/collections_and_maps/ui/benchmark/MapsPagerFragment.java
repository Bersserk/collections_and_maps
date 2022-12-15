package com.example.collections_and_maps.ui.benchmark;


import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class MapsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 2;
    }

    @Override
    public List<ResultItem> createTemplateList() {
        final List<ResultItem> templateList = new ArrayList<>();

        templateList.add(new ResultItem(R.string.HashMap, 0));
        templateList.add(new ResultItem(R.string.TreeMap, 0));

        int[] listMethodsId = {R.string.add_new, R.string.search_key, R.string.removing};

        for (int id : listMethodsId) {
            templateList.add(new ResultItem(0, id));
            for (int i = 0; i < 2; i++) {
                templateList.add(new ResultItem());
            }
        }
        return templateList;
    }






    public String getResult(int methodName) {
        String result;
        double start = System.nanoTime();
        getChose(methodName);
        result = String.valueOf((System.nanoTime() - start) / 1000000);

        return result;
    }

    void getChose(int methodName) {

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


    @Override
    protected ResultItem toRandomValue(ResultItem rItem, int value) {
        if (rItem.result == -1) {
            return rItem;
        } else {
            return new ResultItem(toRandomValue(0, 7));
        }
    }

    public long toRandomValue(int from, int to) {
        double d = from + Math.random() * (to - from);
        long res = (long) (d * 1000);
        try {
            Thread.sleep(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

}
