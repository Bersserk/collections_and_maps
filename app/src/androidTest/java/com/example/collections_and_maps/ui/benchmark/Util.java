package com.example.collections_and_maps.ui.benchmark;

public class Util {
    public static final String INPUT = "1000.0";
    public static final double TIME = 1000.0;
    public static final String PROGRESS_ON = "1.0f";
    public static final String PROGRESS_OFF = "0.0f";
    public static final String EMPTY = "";
    public static final String ANY = "any";

    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
