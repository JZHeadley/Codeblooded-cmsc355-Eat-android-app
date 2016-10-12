package com.jzheadley.eat.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jzheadley.eat.R;


public class MenuCreationActivity extends AppCompatActivity {
    private static final String TAG = "MenuCreationActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_input);
        Button addButton = (Button) findViewById(R.id.Add_btn);

    }
}
