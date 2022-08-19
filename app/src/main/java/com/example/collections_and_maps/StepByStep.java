package com.example.collections_and_maps;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


// my custom class for comfortable reading logs

public class StepByStep {

    static String TAG = "info";


    public static void log(Class<?> aClass, StackTraceElement nameMethod) {

        Pattern pat = Pattern.compile("[A-Z]");
        Matcher match = pat.matcher(aClass.toString());

        int lastCapitalIndex = -1;
        if(match.find())
        {
            lastCapitalIndex = match.start();
        }

        String s = "-->  " + aClass.toString().substring(lastCapitalIndex) + ".";

        Log.i(TAG, s + nameMethod.getMethodName());
    }
}
