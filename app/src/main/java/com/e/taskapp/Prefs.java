package com.e.taskapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Context context) {
    preferences = context.getSharedPreferences("settings", context.MODE_PRIVATE);
    }

    public void setShown(){
        preferences.edit().putBoolean("isShown", true).apply();
    }

    public boolean isShown(){
        return preferences.getBoolean("isShown", false);
    }
}
