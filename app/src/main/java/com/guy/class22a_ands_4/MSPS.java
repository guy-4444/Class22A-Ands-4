package com.guy.class22a_ands_4;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

public class MSPS {

    private static MSPS instance;
    private SharedPreferences prefs;

    public static MSPS getInstance() {
        return instance;
    }

    private MSPS(@NonNull Context context) {
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            prefs = EncryptedSharedPreferences.create(
                    "APP_SPS_DB",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MSPS initHelper(Context context) {
        if (instance == null) {
            instance = new MSPS(context);
        }
        return instance;
    }

    public void putString(String KEY, String value) {
        prefs.edit().putString(KEY, value).apply();
    }

    public String getString(String KEY, String defvalue) {
        return prefs.getString(KEY, defvalue);
    }

    public void putObject(String KEY, Object value) {
        prefs.edit().putString(KEY, new Gson().toJson(value)).apply();
    }

    public <T> T getObject(String KEY, Class<T> mModelClass) {
        Object object = null;
        try {
            object = new Gson().fromJson(prefs.getString(KEY, ""), mModelClass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Primitives.wrap(mModelClass).cast(object);
    }

    public <T> void putArray(String KEY, ArrayList<T> array) {
        String json = new Gson().toJson(array);
        prefs.edit().putString(KEY, json).apply();
    }

    public <T> ArrayList<T> getArray(String KEY, TypeToken typeToken) {
        // type token == new TypeToken<ArrayList<YOUR_CLASS>>() {}
        try {
            ArrayList<T> arr = new Gson().fromJson(prefs.getString(KEY, ""), typeToken.getType());
            if (arr == null) {
                return new ArrayList<>();
            }
            return arr;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public <T> ArrayList<T> getArray(String KEY, Class<T> mModelClass) {
        Type type = TypeToken.getParameterized(ArrayList.class, mModelClass).getType();
        try {
            ArrayList<T> arr = new Gson().fromJson(prefs.getString(KEY, ""), type);
            if (arr == null) {
                return new ArrayList<>();
            }
            return arr;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public <S, T> void putHashMap(String KEY, HashMap<S, T> hashMap) {
        String json = new Gson().toJson(hashMap);
        prefs.edit().putString(KEY, json).apply();
    }

    public <S, T> HashMap<S, T> getHashMap(String KEY, TypeToken typeToken) {
        // type token == new TypeToken<ArrayList<YOUR_CLASS>>() {}
        try {
            HashMap<S, T> map = new Gson().fromJson(prefs.getString(KEY, ""), typeToken.getType());
            if (map == null) {
                return new HashMap<>();
            }
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new HashMap<>();
    }

    public <S, T> HashMap<S, T> getHashMap(String KEY, Class<S> keyClass, Class<T> valueClass) {
        Type type = TypeToken.getParameterized(HashMap.class, keyClass, valueClass).getType();
        try {
            HashMap<S, T> map = new Gson().fromJson(prefs.getString(KEY, ""), type);
            if (map == null) {
                return new HashMap<>();
            }
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new HashMap<>();
    }

    public <T1, T2, T3> void putBigHashMap(String KEY, HashMap<T1, HashMap<T2, T3>> hashMap) {
        String json = new Gson().toJson(hashMap);
        prefs.edit().putString(KEY, json).apply();
    }

    public <T1, T2, T3> HashMap<T1, HashMap<T2, T3>> getBigHashMap(String KEY, Class<T1> keyClass, Class<T2> innerMapKeyClass, Class<T3> innerValueClass) {
        Type type = TypeToken.getParameterized(HashMap.class, keyClass, innerMapKeyClass, innerValueClass).getType();
        try {
            HashMap<T1, HashMap<T2, T3>> map = new Gson().fromJson(prefs.getString(KEY, ""), type);
            if (map == null) {
                return new HashMap<>();
            }
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new HashMap<>();
    }
}