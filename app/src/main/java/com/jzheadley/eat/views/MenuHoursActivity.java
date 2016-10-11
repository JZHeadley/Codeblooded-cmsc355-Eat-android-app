package com.jzheadley.eat.views;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TimePicker;

import com.jzheadley.eat.R;

import java.util.Calendar;

public class MenuHoursActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_hours);
    }

    public void onDoneButtonClick(View view) {
        Intent doneIntent = new Intent(view.getContext(), RestaurantCreationActivity.class);
        view.getContext().startActivity(doneIntent);
        finishActivity(2);
    }

    public void onHoursClick(View v) {
        // TODO Auto-generated method stub
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(v.getContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //TODO show the set time and store it
                    }
                }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }
}
