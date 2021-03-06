package com.jzheadley.eat.ui.restaurantcreation.view;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jzheadley.eat.R;
import com.jzheadley.eat.ui.base.view.BaseActivity;

import java.util.Calendar;

public class OpeningHoursActivity extends BaseActivity {
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


    public void onHoursClick(final View view) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog timePicker;
        timePicker = new TimePickerDialog(view.getContext(),
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String strTimeToShow;
                    String strHrsToShow = (hourOfDay == 0) ? "00" : hourOfDay + "";
                    if (minute == 0) {
                        String zeroMinute = "00";
                        strTimeToShow = strHrsToShow + ":" + zeroMinute;
                    } else if (minute < 10 && minute > 0) {
                        strTimeToShow = strHrsToShow + ":0" + minute;
                    } else {
                        strTimeToShow = strHrsToShow + ":" + minute;
                    }

                    ((TextView) findViewById(view.getId())).setText(strTimeToShow);
                }
            }, hour, minute, true); //Yes 24 hour time
        timePicker.setTitle("Select Time");
        timePicker.show();

    }
}
