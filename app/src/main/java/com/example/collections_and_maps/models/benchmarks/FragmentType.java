package com.example.collections_and_maps.models.benchmarks;

import dagger.Module;

@Module
public class FragmentType implements BenchmarkType{

    private int benchmarkType;

    public FragmentType() {
        this.benchmarkType = 1;
    }

    @Override
    public int getType() {
        return benchmarkType;
    }

    @Override
    public void setType(int value) {
        benchmarkType = value;
    }


}
