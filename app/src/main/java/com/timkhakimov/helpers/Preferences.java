package com.timkhakimov.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Timur Khakimov on 10.02.2018.
 */

public class Preferences {

    private static final String TAG = Preferences.class.toString();
    private static final String APP_PREFERENCES = "com.timkhakimov.app.Preferences";
    private static final int VERSION = 1;

    public static String getAppPreferences(){
        return APP_PREFERENCES + "_" + VERSION;
    }

    public static String getString(Context context, String key){
        return getString(context, key, null);
    }

    public static String getString(Context context, String key, String defaultValue){
        SharedPreferences settings = context.getSharedPreferences(getAppPreferences(), Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    public static void putString(Context context, String key, String value){
        SharedPreferences settings = context.getSharedPreferences(getAppPreferences(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String key){
        return getInt(context, key, 0);
    }

    public static int getInt(Context context, String key, int defValue){
        Log.i(TAG, "getInt, key = " + key + ", defValue = " + defValue);
        SharedPreferences settings = context.getSharedPreferences(getAppPreferences(), Context.MODE_PRIVATE);
        return settings.getInt(key, defValue);
    }

    public static void putInt(Context context, String key, int value){
        Log.i(TAG, "putInt, key = " + key + ", value = " + value);
        SharedPreferences settings = context.getSharedPreferences(getAppPreferences(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key){
        SharedPreferences settings = context.getSharedPreferences(getAppPreferences(), Context.MODE_PRIVATE);
        return settings.getBoolean(key, false);
    }

    public static void putBoolean(Context context, String key, boolean value){
        SharedPreferences settings = context.getSharedPreferences(getAppPreferences(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}
