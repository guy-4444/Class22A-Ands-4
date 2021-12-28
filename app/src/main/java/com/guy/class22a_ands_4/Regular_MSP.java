package com.guy.class22a_ands_4;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class Regular_MSP {

    private static Regular_MSP instance;
    private SharedPreferences prefs;

    public static Regular_MSP getInstance() {
        return instance;
    }

    private Regular_MSP(@NonNull Context context) {
        prefs = context.getApplicationContext().getSharedPreferences("APP_REGULAR_SP_DB", Context.MODE_PRIVATE);
    }

    public static Regular_MSP initHelper(Context context) {
        if (instance == null) {
            instance = new Regular_MSP(context);
        }
        return instance;
    }

    public void putString(String KEY, String value) {
        prefs.edit().putString(KEY, value).apply();
    }

    public String getString(String KEY, String defvalue) {
        return prefs.getString(KEY, defvalue);
    }
}