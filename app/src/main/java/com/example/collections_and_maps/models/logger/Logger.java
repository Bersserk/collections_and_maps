package com.example.collections_and_maps.models.logger;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


// my custom class for comfortable reading logs

public class Logger {

    static String TAG = "info";


    public static void log(Class<?> aClass, StackTraceElement nameMethod) {

        Pattern pat = Pattern.compile("[A-Z]");
        Matcher match = pat.matcher(aClass.toString());

        int lastCapitalIndex = -1;
        if (match.find()) {
            lastCapitalIndex = match.start();
        }

        String s = "-->  " + aClass.toString().substring(lastCapitalIndex) + ".";

        Log.i(TAG, s + nameMethod.getMethodName());
    }

    public static void log(Class<?> aClass, StackTraceElement nameMethod, int spanCount) {

        Pattern pat = Pattern.compile("[A-Z]");
        Matcher match = pat.matcher(aClass.toString());

        int lastCapitalIndex = -1;
        if (match.find()) {
            lastCapitalIndex = match.start();
        }

        String s = "-->  " + aClass.toString().substring(lastCapitalIndex) + ".";
        String end = ": " + new Integer(spanCount).getClass().getCanonicalName() + ": " + spanCount;

        Log.i(TAG, s + nameMethod.getMethodName() + end);

    }

    public static void log(Class<?> aClass, StackTraceElement nameMethod, String t) {

        Pattern pat = Pattern.compile("[A-Z]");
        Matcher match = pat.matcher(aClass.toString());

        int lastCapitalIndex = -1;
        if (match.find()) {
            lastCapitalIndex = match.start();
        }

        String s = "-->  " + aClass.toString().substring(lastCapitalIndex) + ".";
        String end = ": " + new Integer(t).getClass().getCanonicalName() + ": " + t;

        Log.i(TAG, s + nameMethod.getMethodName() + end);

    }

    public static void log(String t, Class<?> aClass, StackTraceElement nameMethod) {

        Pattern pat = Pattern.compile("[A-Z]");
        Matcher match = pat.matcher(aClass.toString());

        int lastCapitalIndex = -1;
        if (match.find()) {
            lastCapitalIndex = match.start();
        }

        String s = "-->  " + aClass.toString().substring(lastCapitalIndex) + ".";
        String end = ": " + t;

        Log.i(TAG, s + nameMethod.getMethodName() + end);

    }
}