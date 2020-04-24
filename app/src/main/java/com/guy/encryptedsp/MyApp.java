package com.guy.encryptedsp;

import android.app.Application;
import android.util.Log;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        MySP.initHelper(this);
        EncyptedSP.initHelper(this);
        MyLibrary.initHelper(this);
    }
}
