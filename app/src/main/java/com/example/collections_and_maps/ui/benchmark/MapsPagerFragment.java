package com.example.collections_and_maps.ui.benchmark;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.MyException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 2;
    }

    @Override
    public List<ResultItem> createTemplateList() {
        return super.createTemplateList(R.array.maps, R.array.maps_item);
    }

    private Map<Integer, String> listForCalculate; // this list for next using in calculation of methods

    public MapsPagerFragment() {
    }

    public MapsPagerFragment(String methodName) {
        switch (methodName){
            case "HashMap":
                listForCalculate = new HashMap<>();
                break;
            case "TreeMap":
                listForCalculate = new TreeMap<>();
                break;
        }
    }

    public String getResult(String methodName) {
        double start = System.nanoTime();
        getChose(methodName);
        result = String.valueOf((System.nanoTime() - start) / 1000000);

        return result;
    }

    void getChose (String methodName){

        switch (methodName) {
            case "addingNew":
                addingNew();
                break;
            case "searchByKey":
                searchByKey();
                break;
            case "removing":
                removing();
                break;
            default:
                try {
                    throw new MyException(R.string.WrongNameMethod);
                } catch (MyException e) {
                    e.printStackTrace();
                }
        }
    }

    public void addingNew() {
        toRandomValue(fromTime, toTime);
//        hashMap.put(i, i);
    }

    protected void searchByKey() {
        toRandomValue(fromTime, toTime);
//        hashMap.get(i).toString();
    }

    private void removing() {
        toRandomValue(fromTime, toTime);
//        hashMap.remove(i);
    }
}
