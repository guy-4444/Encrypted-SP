package com.guy.encryptedsp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EncyptedSP.getInstance().putString("Name", "Guy");


        String name = EncyptedSP.getInstance().getString("Name", "na");
        Log.d("pttt", "Name= " + name);



    }
}
