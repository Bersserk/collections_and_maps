package com.example.collections_and_maps.models.benchmarks;

public class ResultItem {
    private String methodName;
    private String result;
    private String collectionName;
    private int id;

    public ResultItem(String collectionName, String methodName, int id) {
        this.collectionName = collectionName;
        this.methodName = methodName;
        result = "";
        this.id = id;
    }

    public ResultItem(String result, int id) {
        this.result = result;
        this.id = id;
    }

    protected int getId() {
        return id;
    }


    protected String getCollectionName() {
        return collectionName;
    }


    protected String getMethodName() {
        return methodName;
    }

    public String getResult() {
        return result;
    }
}
