package com.sirvan.ltenfinal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.sirvan.ltenfinal.App.G;
import com.sirvan.ltenfinal.Model.DeviceListModel;
import com.sirvan.ltenfinal.R;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ActivityIntro extends AppCompatActivity {

    private LinearLayout l1;
    private Animation uptodown;
    private TextView txtIntro,txtIntroTitle,txtIntroVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Permission();

        l1 =  findViewById(R.id.l1);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        l1.setAnimation(uptodown);

        txtIntro = findViewById(R.id.txtIntro);
        txtIntro.setTypeface(G.face);
        txtIntroTitle = findViewById(R.id.txtIntroTitle);
        txtIntroTitle.setTypeface(G.faceBold);

        txtIntroVersion = findViewById(R.id.txtIntroVersion);
        txtIntroVersion.setTypeface(G.face);
        txtIntroVersion.setText("V " + getVersion());


    }

    private void Permission() {

        Permissions.check(this, new String[]{Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                null, null,
                new PermissionHandler() {
                    @Override
                    public void onGranted() {

                        introToApp();
                    }

                    @Override
                    public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                        introToNoPermissions();
                    }
                });
    }

    private void introToApp() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                if (loadData() == null || loadData().size()==0) {
                    Intent i = new Intent(ActivityIntro.this, ActivityGetNumber.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(ActivityIntro.this, MainActivity.class);
                    startActivity(i);
                }

                finish();
            }
        }, 2500);
    }

    private void introToNoPermissions() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(ActivityIntro.this, ActivityNoPermissions.class);
                startActivity(i);
                finish();
            }
        }, 1500);
    }

    private ArrayList<DeviceListModel> loadData(){
        ArrayList<DeviceListModel> model ;
        SharedPreferences sharedPreferences = getSharedPreferences("listDevice", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<DeviceListModel>>() {
        }.getType();
        model = gson.fromJson(json, type);
        return model;
    }
    private String getVersion() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
