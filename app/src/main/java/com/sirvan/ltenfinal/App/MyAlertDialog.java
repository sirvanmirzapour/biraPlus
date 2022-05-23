package com.sirvan.ltenfinal.App;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.sirvan.ltenfinal.R;

public class MyAlertDialog {
    Context context;
    Activity activity;

    public MyAlertDialog(Activity activity , Context context) {
        this.activity = activity;
        this.context = context;
    }

    public void Show(String notification, final String function,final String number,final String password){
        new FancyAlertDialog.Builder(activity)
                .setTitle("توجه")
                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                .setMessage(notification)
                .setNegativeBtnText("بی خیال")
                .setPositiveBtnBackground(Color.parseColor("#2196F3"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("ارسال")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_settings_remote_white_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        SMSUtils.sendSMS(context, number, "*#" + password + function);
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(context, "دستور لغو شد", Toast.LENGTH_LONG).show();
                    }
                })
                .build();
    }
}
