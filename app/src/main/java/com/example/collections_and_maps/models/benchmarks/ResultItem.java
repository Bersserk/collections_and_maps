package com.example.collections_and_maps.models.benchmarks;

public class ResultItem {
    private final int methodName;
    private long result;
    private final int collectionName;

    public ResultItem(int collectionName, int methodName) {
        this.collectionName = collectionName;
        this.methodName = methodName;
    }

    public int getCollectionName() {
        return collectionName;
    }

    public int getMethodName() {
        return methodName;
    }

    public void setResult(long result) {
        this.result = result;
    }

    public long getResult() {
        return result;
    }
}
