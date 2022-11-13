package com.example.collections_and_maps.models.benchmarks;

import java.util.concurrent.CopyOnWriteArrayList;

public class MyCopyOnWriteArrayList extends Collections {

    public MyCopyOnWriteArrayList(CopyOnWriteArrayList copyOnWriteArrayList, String methodName) {
        super(copyOnWriteArrayList, methodName);
    }
}
