package com.example.collections_and_maps.models.benchmarks;

public class Access {

    private double key;

    protected double getValue() {
        return key;
    }

    protected void setNewKey() {
        this.key = Math.random();
    }
}
