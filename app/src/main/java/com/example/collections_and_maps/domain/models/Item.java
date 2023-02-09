package com.example.collections_and_maps.domain.models;

public abstract class Item {

    public final int headerText;
    public final int methodName;
    public final boolean progressVisible;
    public final double timing;

    public Item(int headerText, int methodName, double timing, boolean progressVisible) {
        this.headerText = headerText;
        this.methodName = methodName;
        this.progressVisible = progressVisible;
        this.timing = timing;
    }
}
