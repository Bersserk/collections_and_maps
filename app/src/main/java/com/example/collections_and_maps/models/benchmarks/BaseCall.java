package com.example.collections_and_maps.models.benchmarks;

public class BaseCall {
    protected final int tillTime = 7000;
    protected final int sinceTime = 0;

    protected String result;
    protected double key;

    public double getKey() {
        return key;
    }

    protected void toRandomValue (int since, int till){
        try {
            double d = since + Math.random() * (till-since);
            Thread.sleep ((long) (d));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
