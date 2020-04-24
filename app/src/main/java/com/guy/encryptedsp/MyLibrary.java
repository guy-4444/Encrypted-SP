package com.guy.encryptedsp;

import android.content.Context;
import android.content.SharedPreferences;

public class MyLibrary {

    // Constructor init + set instance //
    private static MyLibrary instance; // me
    private Context context;

    public static MyLibrary getInstance() {
        return instance;
    }

    private MyLibrary(Context appContext) {
        this.context = appContext;
    }

    public static MyLibrary initHelper(Context context) {
        // in App class : MyLibrary.initHelper(this);
        // don't forget to connect application in Manifest file
        if (instance == null)
            instance = new MyLibrary(context);
        return instance;
    }

    // end of Constructor init + set instance //




    public void someFunc() {
        // do something with context or without it
    }









}
