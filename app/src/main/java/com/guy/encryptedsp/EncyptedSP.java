package com.guy.encryptedsp;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public final class EncyptedSP {

    public interface KEYS {
        public String NAME = "NAME";
        public String ORDER = "ORDER";
        public String ADDRESS = "ADDRESS";
    }


    // Constructor init + set instance //
    private static EncyptedSP instance; // me
    private SharedPreferences sharedPreferences;

    public static EncyptedSP getInstance() {
        return instance;
    }

    private EncyptedSP(Context appContext) {
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            sharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    masterKeyAlias,
                    appContext,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EncyptedSP initHelper(Context context) {
        if (instance == null)
            instance = new EncyptedSP(context);
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