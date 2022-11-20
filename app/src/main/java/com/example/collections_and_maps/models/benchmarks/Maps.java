package com.example.collections_and_maps.models.benchmarks;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Maps extends BaseCall{

    private Map <Integer, String> listForCalculate; // this list for next using in calculation of methods

    public Maps(Access key) {
        this.key = key.getValue();
    }

    public Maps(String methodName) {
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
                result = "нет такого поля";
        }
    }

    public void addingNew() {
        toRandomValue(sinceTime, tillTime);
//        hashMap.put(i, i);
    }

    protected void searchByKey() {
        toRandomValue(sinceTime, tillTime);
//        hashMap.get(i).toString();
    }

    private void removing() {
        toRandomValue(sinceTime, tillTime);
//        hashMap.remove(i);
    }

}
