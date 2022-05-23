package com.sirvan.ltenfinal.App;

import android.app.Activity;
import android.content.Context;
import com.sirvan.ltenfinal.R;
import com.tapadoo.alerter.Alerter;
import ir.vasl.globalEnums.EnumNotificationType;
import ir.vasl.magicalnotifier.MagicalNotifier;

public class Notification {

    public void ShowNotification(Activity activity, String name, String sms){
        Alerter.create(activity)
                .setTitle(name)
                .setTitleTypeface(G.faceBold)
                .setText(sms)
                .setTextTypeface(G.face)
                .enableSwipeToDismiss()
                .setBackgroundColorRes(R.color.colorPrimary)
                .show();
    }
    public void MyNotification(Context context, String name, String sms){
        new MagicalNotifier.Builder(context)
                .setNotificationType(EnumNotificationType.SIMPLE)
                .setTitle(name)
                .setSubTitle(sms)
                .show();
    }
}
