package com.guy.encryptedsp;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Set;


public final class Temp {
    private static Temp instance;
    private SharedPreferences sharedPreferences;

    private static final boolean DEFAULT_BOOLEAN_VAL  = false;
    private static final float DEFAULT_FLOAT_VAL = 0.0F;
    private static final int DEFAULT_INT_VAL = -1;
    private static final long DEFAULT_LONG_VAL = -1L;
    private static final double DEFAULT_DOUBLE_VAL = 0.0;
    private static final String DEFAULT_STRING_VAL = "";
    private static final Set<String> DEFAULT_SET_VAL = null;

    private Temp(Context context) {
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            sharedPreferences = EncryptedSharedPreferences.create(
                    context.getPackageName() + "_preferences",
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

    public static Temp getInstance(Context appContext) {
        //use only application context to avoid memory leaks
        if (instance == null) {
            instance = new Temp(appContext.getApplicationContext());
        }

        return instance;
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, DEFAULT_BOOLEAN_VAL);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, DEFAULT_FLOAT_VAL);
    }

    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, DEFAULT_INT_VAL);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, DEFAULT_LONG_VAL);
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public double getDouble(String key) {
        if(!contains(key))
            return DEFAULT_DOUBLE_VAL;

        return Double.longBitsToDouble(getLong(key));
    }

    public double getDouble(String key, double defaultValue) {
        if(!contains(key))
            return defaultValue;

        return Double.longBitsToDouble(getLong(key, DEFAULT_LONG_VAL));
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, DEFAULT_STRING_VAL);
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public Set<String> getStringSet(String key) {
        return sharedPreferences.getStringSet(key, DEFAULT_SET_VAL);
    }

    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }

    public void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean putBooleanSync(String key, boolean value) {
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public void putFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public boolean putFloatSync(String key, float value) {
        return sharedPreferences.edit().putFloat(key, value).commit();
    }

    public void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public boolean putIntSync(String key, int value) {
        return sharedPreferences.edit().putInt(key, value).commit();
    }

    public void putLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public boolean putLongSync(String key, long value) {
        return sharedPreferences.edit().putLong(key, value).commit();
    }

    public void putDouble(String key, double value) {
        putLong(key, Double.doubleToRawLongBits(value));
    }

    public boolean putDoubleSync(String key, double value) {
        return putLongSync(key, Double.doubleToRawLongBits(value));
    }

    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public boolean putStringSync(String key, String value) {
        return sharedPreferences.edit().putString(key, value).commit();
    }

    public void putStringSet(String key, Set<String> value) {
        sharedPreferences.edit().putStringSet(key, value).apply();
    }

    public boolean putStringSetSync(String key, Set<String> value) {
        return sharedPreferences.edit().putStringSet(key, value).commit();
    }

    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public boolean removeSync(String key) {
        return sharedPreferences.edit().remove(key).commit();
    }

    public void removeAll() {
        sharedPreferences.edit().clear().apply();
    }

    public boolean removeAllSync() {
        return sharedPreferences.edit().clear().commit();
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }
}