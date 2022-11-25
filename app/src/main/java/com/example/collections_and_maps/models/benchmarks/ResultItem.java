package com.example.collections_and_maps.models.benchmarks;

public class ResultItem {
    private String methodName;
    private String result;
    private String collectionName;

    public ResultItem(String collectionName, String methodName) {
        this.collectionName = collectionName;
        this.methodName = methodName;
        result = "";
    }

    public ResultItem(String result) {
        this.result = result;
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
