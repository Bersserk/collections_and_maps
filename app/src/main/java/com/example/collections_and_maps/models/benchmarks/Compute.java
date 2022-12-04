package com.example.collections_and_maps.models.benchmarks;

public class Compute {

    private ResultItem resultItem;

    public ResultItem getResultItem() {
        return resultItem;
    }

    public Compute(ResultItem resultItem) {
        if (resultItem.headerText == 0 && resultItem.methodName == 0) {
            this.resultItem = new ResultItem(toRandomValue(0, 5));
        } else {
            this.resultItem = resultItem;
        }
    }

    private long toRandomValue(int since, int till) {
        double d = since + Math.random() * (till - since);
        long res = (long) (d * 1000);
        try {
            Thread.sleep(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

}
