package com.ezbuy.ezpopup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ezbuy.library.EzPopup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new EzPopup().init();
    }
}
