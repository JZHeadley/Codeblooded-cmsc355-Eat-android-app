package com.jzheadley.eat.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jzheadley.eat.R;

/**
 * Created by CJP on 10/10/2016.
 */
public class MenuHoursActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_hours);
    }

    public void onDoneButtonClick(View view) {
        Intent doneIntent = new Intent(view.getContext(), RestaurantCreationActivity.class);
        view.getContext().startActivity(doneIntent);
    }
}
