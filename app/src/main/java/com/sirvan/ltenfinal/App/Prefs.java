package com.sirvan.ltenfinal.App;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    public SharedPreferences setting;
    private static final String PREFERENCES = "MY_PREFERENCES";
    public static final String NUMBER = "NUMBER";
    public static final String PASS = "PASS";
    public static final String NAME = "NAME";

    public static final String NUMBER1 = "NUMBER";
    public static final String PASS1 = "PASS";
    public static final String NAME1 = "NAME";

    public static final String NUMBER2 = "NUMBER";
    public static final String PASS2 = "PASS";
    public static final String NAME2 = "NAME";

    public static final String NUMBER3 = "NUMBER";
    public static final String PASS3 = "PASS";
    public static final String NAME3 = "NAME";

    public static final String NUMBER4 = "NUMBER";
    public static final String PASS4 = "PASS";
    public static final String NAME4 = "NAME";

    public Prefs(Context context) {
        setting = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public String getData(String key) {
        return setting.getString(key, "err");
    }

    public void setData(String key, String value) {
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
