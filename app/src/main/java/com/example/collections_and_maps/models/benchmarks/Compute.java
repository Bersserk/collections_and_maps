package com.example.collections_and_maps.models.benchmarks;

public class Compute {

    private ResultItem resultItem;

    public ResultItem getResultItem() {
        return resultItem;
    }

    public Compute() {
            this.resultItem = new ResultItem(0,0, toRandomValue(0, 5));
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
