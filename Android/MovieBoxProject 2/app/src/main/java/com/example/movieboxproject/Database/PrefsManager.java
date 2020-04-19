package com.example.movieboxproject.Database;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {
    private static PrefsManager instance;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    private PrefsManager(Context context) {
        preferences = context.getSharedPreferences("moviebox_prefs", Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public static PrefsManager getInstance(Context context){
        if (instance==null){
            instance = new PrefsManager(context);
        }
        return instance;
    }

    public void writeInt(String key, int value){
        editor.putInt(key, value).commit();
    }

    public void writeString(String key, String value){
        editor.putString(key, value).commit();
    }

    public int readInt(String key){
        return preferences.getInt(key, 0);
    }

    public String readString(String key){
        return preferences.getString(key, "");
    }
}
