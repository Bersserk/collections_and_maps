package com.example.collections_and_maps.models.logger;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// my custom class for comfortable reading logs

public class Logger {

    static String TAG = "info";
    private static Field[] declaredFields;
    private static Object object;
    private static Object object1;


    public static void mlog(Class<?> aClass, StackTraceElement nameMethod) {

        Pattern pat = Pattern.compile("[A-Z]");
        Matcher match = pat.matcher(aClass.toString());

        int lastCapitalIndex = -1;
        if (match.find()) {
            lastCapitalIndex = match.start();
        }

        String s = "-->  " + aClass.toString().substring(lastCapitalIndex) + ".";

        Log.i(TAG, s + nameMethod.getMethodName());
    }

    public static void mlog(Class<?> aClass, StackTraceElement nameMethod, int spanCount) {

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

    public static void mlog(Class<?> aClass, StackTraceElement nameMethod, String t) {

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

    public static void mlog(String t, Class<?> aClass, StackTraceElement nameMethod) {

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

    public static void mlog(String tag, String outPut, int value) {
        Log.i(tag, outPut + " = " + value);
    }

    public static void mlog(String tag, String outPut, List<?> list) {
        int i = 0;
        StringBuilder s = new StringBuilder();
        for (Object v: list) {

            s.append("<"+i+++">");
        }



        Log.i(tag, outPut + " = " + s);
    }

    private void print(String service) {
        System.out.println(service);
    }

    public void print(String b1, Object o) {
        if(o == null){
            System.out.println(b1 + " = null");
        } else {
            System.out.println(b1 + " = " + o);
        }
    }

}
