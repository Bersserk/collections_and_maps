package com.example.collections_and_maps.view_model.models;

public class FragmentData {
    public final int namePage;
    public final int[] listHeadsId;
    public final int[] listMethodsId;

    public FragmentData(int[] listHeadsId, int[] listMethodsId, int namePage) {
        this.listHeadsId = listHeadsId;
        this.listMethodsId = listMethodsId;
        this.namePage = namePage;
    }
}
