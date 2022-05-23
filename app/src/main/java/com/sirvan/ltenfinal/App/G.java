package com.sirvan.ltenfinal.App;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

public class G extends Application {
    public static Context context;
    public static Typeface face;
    public static Typeface faceBold;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        face =  Typeface.createFromAsset(context.getAssets(), "font/Samim.ttf");
        faceBold = Typeface.createFromAsset(context.getAssets(),"font/Samim-Bold.ttf");


    }
}
