package com.a1.nextlocation.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.a1.nextlocation.R;

public class MainActivity extends AppCompatActivity {

    /**
     * onCreate method that creates the main activity
     * @param savedInstanceState the saved instance state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}