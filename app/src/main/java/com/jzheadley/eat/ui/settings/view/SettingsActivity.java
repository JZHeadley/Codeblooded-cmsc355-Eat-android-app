package com.jzheadley.eat.ui.settings.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.view.BaseActivity;


public class SettingsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
