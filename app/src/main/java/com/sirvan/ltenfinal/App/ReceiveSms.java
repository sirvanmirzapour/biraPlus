package com.sirvan.ltenfinal.App;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sirvan.ltenfinal.Model.DeviceListModel;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ReceiveSms extends BroadcastReceiver {

    private String senderNum;
    private String message;
    private int index;
    private List<String> number = new ArrayList<>();
    List<String> listNumber = new ArrayList<>();

    public void onReceive(Context context, Intent intent) {
        listNumber.clear();
        listNumber = getNumber();
        number.clear();

        for (int i = 0; i < listNumber.size(); i++) {
            number.add(Function.convertMobileNumber(listNumber.get(i)));
        }

        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    senderNum = phoneNumber;
                    message = currentMessage.getDisplayMessageBody();

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            //   Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }

        if (search(number, senderNum)) {
            Notification notification = new Notification();
            notification.MyNotification(context, getName().get(index), message);
        }

    }

    private List<String> getNumber() {
        ArrayList<DeviceListModel> model = new ArrayList<>(loadData());
        List<String> listNumber = new ArrayList<>();
        if (model == null) {
            listNumber.add("");
        } else {
            for (int i = 0; i < model.size(); i++) {
                listNumber.add(model.get(i).getNumber());
            }
        }
        return listNumber;
    }

    private List<String> getName() {
        ArrayList<DeviceListModel> model = new ArrayList<>(loadData());
        List<String> listName = new ArrayList<>();
        if (model == null) {
            listName.add("");
        } else {
            for (int i = 0; i < model.size(); i++) {
                listName.add(model.get(i).getName());
            }
        }
        return listName;
    }

    private ArrayList<DeviceListModel> loadData() {
        ArrayList<DeviceListModel> model;
        SharedPreferences sharedPreferences = G.context.getSharedPreferences("listDevice", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<DeviceListModel>>() {
        }.getType();
        model = gson.fromJson(json, type);
        return model;
    }

    private boolean search(List<String> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (str.contains(list.get(i))) {
                index = i;
                return true;
            }
        }
        return false;
    }
}

