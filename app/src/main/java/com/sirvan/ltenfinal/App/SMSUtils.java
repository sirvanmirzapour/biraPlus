package com.sirvan.ltenfinal.App;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.telephony.SmsManager;
import android.widget.Toast;
import com.sirvan.ltenfinal.R;

import java.util.ArrayList;

public class SMSUtils extends BroadcastReceiver {

    public static final String SENT_SMS_ACTION_NAME = "SMS_SENT";
    public static final String DELIVERED_SMS_ACTION_NAME = "SMS_DELIVERED";

    private static final String TELEPHON_NUMBER_FIELD_NAME = "address";
    private static final String MESSAGE_BODY_FIELD_NAME = "body";
    private static final Uri SENT_MSGS_CONTET_PROVIDER = Uri.parse("content://sms/sent");

    @Override
    public void onReceive(Context context, Intent intent) {
        //Detect l'envoie de sms
        if (intent.getAction().equals(SENT_SMS_ACTION_NAME)) {
            switch (getResultCode()) {
                case Activity.RESULT_OK: // Sms sent
                    Toast.makeText(context, context.getString(R.string.sms_send), Toast.LENGTH_LONG).show();
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE: // generic failure
                    Toast.makeText(context, context.getString(R.string.sms_not_send), Toast.LENGTH_LONG).show();
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE: // No service
                    Toast.makeText(context, context.getString(R.string.sms_not_send_no_service), Toast.LENGTH_LONG).show();
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU: // null pdu
                    Toast.makeText(context, context.getString(R.string.sms_not_send), Toast.LENGTH_LONG).show();
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF: //Radio off
                    Toast.makeText(context, context.getString(R.string.sms_not_send_no_radio), Toast.LENGTH_LONG).show();
                    break;
            }
        }
        //detect la reception d'un sms
        else if (intent.getAction().equals(DELIVERED_SMS_ACTION_NAME)) {
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Toast.makeText(context, context.getString(R.string.sms_deleverd), Toast.LENGTH_LONG).show();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(context, context.getString(R.string.sms_not_receive), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    /**
     * Test if device can send SMS
     * @param context
     * @return
     */
    public static boolean canSendSMS(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
    }

    public static void sendSMS(final Context context, String phoneNumber, String message) {

        if (!canSendSMS(context)) {
            Toast.makeText(context, context.getString(R.string.cannot_send_sms), Toast.LENGTH_LONG).show();
            return;
        }

        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, new Intent(SENT_SMS_ACTION_NAME), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0, new Intent(DELIVERED_SMS_ACTION_NAME), 0);

        final SMSUtils smsUtils = new SMSUtils();
        //register for sending and delivery
        context.registerReceiver(smsUtils, new IntentFilter(SMSUtils.SENT_SMS_ACTION_NAME));
        context.registerReceiver(smsUtils, new IntentFilter(DELIVERED_SMS_ACTION_NAME));

        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(message);

        ArrayList<PendingIntent> sendList = new ArrayList<>();
        sendList.add(sentPI);

        ArrayList<PendingIntent> deliverList = new ArrayList<>();
        deliverList.add(deliveredPI);

     sms.sendMultipartTextMessage(phoneNumber, null, parts, sendList, deliverList);
        addMessageToSent( phoneNumber,  message,  context);
        //we unsubscribed in 10 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                context.unregisterReceiver(smsUtils);
            }
        }, 10000);

    }

    private static void addMessageToSent(String telNumber, String messageBody, Context context) {
        ContentValues sentSms = new ContentValues();
        sentSms.put(TELEPHON_NUMBER_FIELD_NAME, telNumber);
        sentSms.put(MESSAGE_BODY_FIELD_NAME, messageBody);

        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.insert(SENT_MSGS_CONTET_PROVIDER, sentSms);
    }
}
