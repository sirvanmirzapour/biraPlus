package com.sirvan.ltenfinal.App;

import android.content.Context;

public class Function {
    private Context context;
    private Prefs prefs;

    public Function(Context context) {
        this.context = context;
    }
    public static String convertMobileNumber(String mobile_number) {
        if (mobile_number != null) {
            if (mobile_number.length() < 10) {
                return "00000000";
            } else {
                return mobile_number.substring(1, mobile_number.length());
            }
        } else {
            return "00000000";
        }

    }
}
