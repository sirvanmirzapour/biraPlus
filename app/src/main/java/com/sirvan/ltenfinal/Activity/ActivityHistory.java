package com.sirvan.ltenfinal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.sirvan.ltenfinal.Adapter.HistoryAdapter;
import com.sirvan.ltenfinal.App.Function;
import com.sirvan.ltenfinal.App.G;
import com.sirvan.ltenfinal.App.Prefs;
import com.sirvan.ltenfinal.Model.SmsModels;
import com.sirvan.ltenfinal.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import saman.zamani.persiandate.PersianDate;

public class ActivityHistory extends AppCompatActivity {

    private String strNumber, strName, strPassword;
    private RecyclerView rvHistory;
    private HistoryAdapter adapter;
    private List<SmsModels> lstSms = new ArrayList<>();
    private Animation slideUp;
    private TextView txtToolBarHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        strNumber = getIntent().getExtras().getString("number");
        strPassword = getIntent().getExtras().getString("password");
        strName = getIntent().getExtras().getString("name");

        Toolbar toolbar = findViewById(R.id.toolbarHistory);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Ini();
        DataToRecyclerView();

        txtToolBarHistory.setText("تاریخچه " + strName);
    }

    private void Ini() {
        rvHistory = findViewById(R.id.rvHistory);
        txtToolBarHistory = findViewById(R.id.txtToolBarHistory);
        txtToolBarHistory.setTypeface(G.faceBold);

    }

    private void DataToRecyclerView() {
        LinearLayoutManager llmVertical = new LinearLayoutManager(this);
        llmVertical.setReverseLayout(true);
        rvHistory.setLayoutManager(llmVertical);
        rvHistory.setHasFixedSize(true);
        slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        adapter = new HistoryAdapter(this, getMessage());
        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(adapter);
        alphaAdapter.setFirstOnly(false);
        rvHistory.setAdapter(alphaAdapter);
    }

    private List<SmsModels> getMessage() {
        List<SmsModels> data = new ArrayList<>();
        String isMobileNum = Function.convertMobileNumber(strNumber);
        try {
            Uri a = Uri.parse("content://sms");
            Cursor cr = getContentResolver().query(a, new String[]{"_id", "address", "date", "status", "body", "type"}, null, null, null);
            if (cr != null) {
                cr.moveToFirst();
                do {
                    if (cr.getString(1).contains(isMobileNum)) {
                        SmsModels sms = new SmsModels();
                        sms.setDate(getDateFromMessage(cr.getString(2)));
                        sms.setMsg(smsRender(cr.getString(4)));
                        sms.setTime(getTimeFromMessage(cr.getString(2)));
                        if (cr.getString(5).equals("1")) {
                            sms.setLayoutID(1);
                            sms.setImage(R.drawable.ic_subdirectory_arrow_right_light_blue_a700_18dp);
                        } else {
                            sms.setLayoutID(2);
                            if (cr.getString(3).equals("0")) {
                                sms.setImage(R.drawable.ic_done_all_light_green_a700_18dp);
                            } else {
                                sms.setImage(R.drawable.ic_close_red_a700_18dp);
                            }
                        }
                        data.add(sms);
                    }
                } while (cr.moveToNext());
            }
            cr.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exc :" + e, Toast.LENGTH_LONG).show();
        }
        return data;

    }

    private String getDateFromMessage(String date) {

        long currentTime = Long.parseLong(date);
        PersianDate persianDate = new PersianDate(currentTime);
        return persianDate.getShYear() + "-" + persianDate.getShMonth() + "-" + persianDate.getShDay();
    }

    private String getTimeFromMessage(String dateString) {
        long currentTime = Long.parseLong(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        int mHour = calendar.get(Calendar.HOUR);
        int mMinute = calendar.get(Calendar.MINUTE);
        return mHour + ":" + mMinute;
    }

    private String smsRender(String str) {

        if (str.contains("*#" + strPassword + "*11*1#")) {
            return "درخواست روشن شدن دستگاه یک در کاربرد یکم";
        } else if (str.contains("*#" + strPassword + "*11*2#")) {
            return "درخواست خاموش شدن دستگاه یک در کاربرد یکم";
        } else if (str.contains("*#" + strPassword + "*12*1#")) {
            return "درخواست روشن شدن دستگاه دوم در کاربرد یکم";
        } else if (str.contains("*#" + strPassword + "*12*2#")) {
            return "درخواست خاموش شدن دستگاه دوم در کاربرد یکم";
        } else if (str.contains("*#" + strPassword + "*10#")) {
            return "درخواست وضعیت دستگاه در کاربرد یکم";
        } else if (str.contains("*#" + strPassword + "*20#")) {
            return "درخواست وضعیت دستگاه در کاربرد دوم";
        } else if (str.contains("*#" + strPassword + "*21*1#")) {
            return "درخواست روشن شدن دستگاه یکم در کاربرد دوم";
        } else if (str.contains("*#" + strPassword + "*21*2#")) {
            return "درخواست خاموش شدن دستگاه یکم در کاربرد دوم";
        } else if (str.contains("*#" + strPassword + "*22*1#")) {
            return "درخواست وصل شدن کلید یکم در کاربرد دوم";
        } else if (str.contains("*#" + strPassword + "*22*2#")) {
            return "درخواست قطع شدن کلید یکم در کاربرد دوم";
        } else if (str.contains("*#" + strPassword + "*23*1#")) {
            return "درخواست وصل شدن کلید دوم در کاربرد دوم";
        } else if (str.contains("*#" + strPassword + "*23*2#")) {
            return "درخواست قطع شدن کلید دوم در کاربرد دوم";
        } else if (str.contains("*#" + strPassword + "*30#")) {
            return "درخواست وضعیت دستگاه در کاربرد دوم";
        } else if (str.contains("*#" + strPassword + "*31*1#")) {
            return "درخواست بالا بردن کرکره برقی در کاربرد سوم";
        } else if (str.contains("*#" + strPassword + "*31*2#")) {
            return "درخواست پایین آوردن کرکره برقی در کاربرد سوم";
        } else if (str.contains("*#" + strPassword + "*32*1#")) {
            return "درخواست وصل شدن کلید یکم در کاربرد سوم";
        } else if (str.contains("*#" + strPassword + "*32*2#")) {
            return "درخواست قطع شدن کلید یکم در کاربرد سوم";
        } else if (str.contains("*#" + strPassword + "*33*1#")) {
            return "درخواست وصل شدن کلید دوم در کاربرد سوم";
        } else if (str.contains("*#" + strPassword + "*33*2#")) {
            return "درخواست قطع شدن کلید دوم در کاربرد سوم";
        } else if (str.contains("*#" + strPassword + "*40#")) {
            return "درخواست وضعیت دستگاه در کاربرد سوم";
        } else if (str.contains("*#" + strPassword + "*41*1#")) {
            return "درخواست بالا بردن کرکره برقی در کاربرد چهارم";
        } else if (str.contains("*#" + strPassword + "*41*2#")) {
            return "درخواست پایین آوردن کرکره برقی در کاربرد چهارم";
        } else if (str.contains("*#" + strPassword + "*42*1#")) {
            return "درخواست فعال کردن دزدگیر در کاربرد چهارم";
        } else if (str.contains("*#" + strPassword + "*42*2#")) {
            return "درخواست غیر فعال کردن دزدگیر در کاربرد چهارم";
        } else if (str.contains("*#" + strPassword + "*80*1#")) {
            return "درخواست دریافت پیامک هنگام وصل شدن برق اصلی دستگاه";
        } else if (str.contains("*#" + strPassword + "*80*2#")) {
            return "درخواست عدم دریافت پیامک هنگام وصل شدن برق اصلی دستگاه";
        } else if (str.contains("*#" + strPassword + "*80*3#")) {
            return "درخواست دریافت پیامک هنگام قطع شدن برق اصلی دستگاه";
        } else if (str.contains("*#" + strPassword + "*80*4#")) {
            return "درخواست عدم دریافت پیامک هنگام قطع شدن برق اصلی دستگاه";
        } else if (str.contains("*#" + strPassword + "*80*0#")) {
            return "درخواست وضعیت هشدارها هنگام قطع و وصل شدن برق اصلی دستگاه";
        } else if (str.contains("*#" + strPassword + "*82*1#")) {
            return "درخواست تغییر وضعبت هشدارها به حالت پیامک و تماس";
        } else if (str.contains("*#" + strPassword + "*82*2#")) {
            return "درخواست تغییر وضعبت هشدارها به حالت پیامک ";
        } else if (str.contains("*#" + strPassword + "*82*0#")) {
            return "درخواست دریافت نحوه اعلان هشدارها";
        } else if (str.contains("*#" + strPassword + "*81*1#")) {
            return "درخواست فعال شدن هشدار هنگام باز و بسته شدن کرکره برقی";
        } else if (str.contains("*#" + strPassword + "*81*2#")) {
            return "درخواست غیرفعال شدن هشدار هنگام باز و بسته شدن کرکره برقی";
        } else if (str.contains("*#" + strPassword + "*81*0#")) {
            return "درخواست وضعیت هشدار باز و بسته بودن کرکره برقی";
        } else if (str.contains("*#" + strPassword + "*03*0#")) {
            return "درخواست دریافت شماره سریال دستگاه";
        }else return str;

    }
}
