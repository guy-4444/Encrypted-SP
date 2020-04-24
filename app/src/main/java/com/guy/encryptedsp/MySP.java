package com.guy.encryptedsp;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MySP {

    public interface KEYS {
        public String NAME = "NAME";
        public String ORDER = "ORDER";
        public String ADDRESS = "ADDRESS";
    }


    // Constructor init + set instance //
    private static MySP instance; // me
    private SharedPreferences sharedPreferences;

    public static MySP getInstance() {
        return instance;
    }

    private MySP(Context appContext) {
        sharedPreferences = appContext.getApplicationContext().getSharedPreferences("APP_SP_DB", Context.MODE_PRIVATE);
    }

    public static MySP initHelper(Context context) {
        if (instance == null)
            instance = new MySP(context);
        return instance;
    }

    // end of Constructor init + set instance //




    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public boolean putStringSync(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }








}
