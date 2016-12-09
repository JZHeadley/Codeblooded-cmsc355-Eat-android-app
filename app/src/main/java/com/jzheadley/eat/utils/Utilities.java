package com.jzheadley.eat.utils;

import android.text.TextUtils;

public class Utilities {
    public static double convertMetersToMiles(double meters) {
        return (meters / 1609.344);
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
