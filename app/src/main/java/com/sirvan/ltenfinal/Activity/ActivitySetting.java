package com.sirvan.ltenfinal.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sirvan.ltenfinal.App.G;
import com.sirvan.ltenfinal.App.MyAlertDialog;
import com.sirvan.ltenfinal.App.SMSUtils;
import com.sirvan.ltenfinal.R;

public class ActivitySetting extends AppCompatActivity {

    private String strNumber,strPassword,strName;

    private LinearLayout llActivePowerOn, llUnActivePowerOn;
    private TextView txtActivePowerOn, txtUnActivePowerOn, txtPowerOn;
    private LinearLayout llActivePowerOff, llUnActivePowerOff;
    private TextView txtActivePowerOff, txtUnActivePowerOff, txtPowerOff;
    private LinearLayout llSmsSituation;
    private TextView txtSmsSituation;
    private TextView txtAlert,txtAlertWithSms,txtAlertSituation,txrAlertWithSmsAndCall;
    private LinearLayout llAlertWithSms,llAlertSituation,llAlertWithSmsAndCall;
    private TextView txtDoor,txtActiveDoor,txtDoorSituation,txtUnActiveDoor;
    private LinearLayout llActiveDoor,llDoorSituation,llUnActiveDoor;

    private LinearLayout llEMI;
    private TextView txtEMI;

    private TextView txtToolBarSetting;
    private MyAlertDialog myAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = findViewById(R.id.toolbarSetting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        myAlertDialog = new MyAlertDialog(ActivitySetting.this,getApplicationContext());

        strNumber = getIntent().getExtras().getString("number");
        strPassword = getIntent().getExtras().getString("password");
        strName = getIntent().getExtras().getString("name");

        Ini();
        setTypeFace();
        ClickOnOffSms();
        ClickAlertChange();
        ClickDoorSetting();
        ClickEMI();

        txtToolBarSetting.setText("تنظیمات "+ strName);
    }

    private void Ini() {
        llActivePowerOn = findViewById(R.id.llActivePowerOn);
        llUnActivePowerOn = findViewById(R.id.llUnActivePowerOn);
        txtActivePowerOn = findViewById(R.id.txtActivePowerOn);
        txtUnActivePowerOn = findViewById(R.id.txtUnActivePowerOn);
        txtPowerOn = findViewById(R.id.txtPowerOn);

        llActivePowerOff = findViewById(R.id.llActivePowerOff);
        llUnActivePowerOff = findViewById(R.id.llUnActivePowerOff);
        txtActivePowerOff = findViewById(R.id.txtActivePowerOff);
        txtUnActivePowerOff = findViewById(R.id.txtUnActivePowerOff);
        txtPowerOff = findViewById(R.id.txtPowerOff);

        llSmsSituation = findViewById(R.id.llSmsSituation);
        txtSmsSituation = findViewById(R.id.txtSmsSituation);

        txtAlert = findViewById(R.id.txtAlert);
        txtAlertWithSms = findViewById(R.id.txtAlertWithSms);
        txtAlertSituation = findViewById(R.id.txtAlertSituation);
        txrAlertWithSmsAndCall = findViewById(R.id.txtAlertWithSmsAndCall);

        llAlertWithSms = findViewById(R.id.llAlertWithSms);
        llAlertSituation = findViewById(R.id.llAlertSituation);
        llAlertWithSmsAndCall = findViewById(R.id.llAlertWithSmsAndCall);


        txtDoor = findViewById(R.id.txtDoor);
        txtActiveDoor = findViewById(R.id.txtActiveDoor);
        txtDoorSituation = findViewById(R.id.txtDoorSituation);
        txtUnActiveDoor = findViewById(R.id.txtUnActiveDoor);

        llActiveDoor = findViewById(R.id.llActiveDoor);
        llDoorSituation = findViewById(R.id.llDoorSituation);
        llUnActiveDoor = findViewById(R.id.llUnActiveDoor);

        llEMI = findViewById(R.id.llEMI);
        txtEMI = findViewById(R.id.txtEMI);

        txtToolBarSetting = findViewById(R.id.txtToolBarSetting);

    }

    private void setTypeFace() {
        txtPowerOff.setTypeface(G.faceBold);
        txtPowerOn.setTypeface(G.faceBold);

        txtActivePowerOff.setTypeface(G.face);
        txtUnActivePowerOff.setTypeface(G.face);

        txtSmsSituation.setTypeface(G.face);

        txtAlert.setTypeface(G.faceBold);
        txtAlertWithSms.setTypeface(G.face);
        txtAlertSituation.setTypeface(G.face);
        txrAlertWithSmsAndCall.setTypeface(G.face);

        txtDoor.setTypeface(G.faceBold);
        txtActiveDoor.setTypeface(G.face);
        txtDoorSituation.setTypeface(G.face);
        txtUnActiveDoor.setTypeface(G.face);

        txtEMI.setTypeface(G.face);

        txtToolBarSetting.setTypeface(G.faceBold);

    }

    private void ClickOnOffSms(){
        llActivePowerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Alert("دریافت پیامک فعال شود؟","*80*1#");
                myAlertDialog.Show("دریافت پیامک فعال شود؟","*80*1#",strNumber,strPassword);
            }
        });
        llUnActivePowerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Alert("دریافت پیامک غیر فعال شود؟","*80*2#");
                myAlertDialog.Show("دریافت پیامک غیر فعال شود؟","*80*2#",strNumber,strPassword);

            }
        });
        llActivePowerOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Alert("دریافت پیامک فعال شود؟","*80*3#");
                myAlertDialog.Show("دریافت پیامک فعال شود؟","*80*3#",strNumber,strPassword);
            }
        });
        llUnActivePowerOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   Alert("دریافت پیامک غیر فعال شود؟","*80*4#");
                myAlertDialog.Show("دریافت پیامک غیر فعال شود؟","*80*4#",strNumber,strPassword);
            }
        });

        llSmsSituation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Alert("دریافت وضعیت پیامک ها؟","*80*0#");
                myAlertDialog.Show("دریافت وضعیت پیامک ها؟","*80*0#",strNumber,strPassword);
            }
        });
    }

    private void ClickAlertChange(){
        llAlertWithSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Alert("دریافت هشدارها فقط از طریق پیامک؟","*82*2#");
                myAlertDialog.Show("دریافت هشدارها فقط از طریق پیامک؟","*82*2#",strNumber,strPassword);
            }
        });
        llAlertWithSmsAndCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Alert("دریافت هشداها از طریق پیامک و تماس؟","*82*1#");
                myAlertDialog.Show("دریافت هشداها از طریق پیامک و تماس؟","*82*1#",strNumber,strPassword);
            }
        });
        llAlertSituation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Alert("دریافت وضعیت هشدارها؟","*82*0#");
                myAlertDialog.Show("دریافت وضعیت هشدارها؟","*82*0#",strNumber,strPassword);
            }
        });
    }

    private void ClickDoorSetting(){
        llActiveDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Alert("هشدار باز و بسته شدن کرکره برقی فعال شود؟","*81*1#");
                myAlertDialog.Show("هشدار باز و بسته شدن کرکره برقی فعال شود؟","*81*1#",strNumber,strPassword);
            }
        });

        llUnActiveDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Alert("هشدار باز و بسته شدن کرکره برقی غیرفعال شود؟","*81*2#");
                myAlertDialog.Show("هشدار باز و بسته شدن کرکره برقی غیرفعال شود؟","*81*2#",strNumber,strPassword);
            }
        });

        llDoorSituation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Alert("دریافت وضعیت کرکره برقی؟","*81*0#");
                myAlertDialog.Show("دریافت وضعیت کرکره برقی؟","*81*0#",strNumber,strPassword);
            }
        });
    }

    private void ClickEMI(){
        llEMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Alert("دریافت شماره سریال دستگاه؟", "*03*0#");
                myAlertDialog.Show("دریافت شماره سریال دستگاه؟", "*03*0#",strNumber,strPassword);
            }
        });
    }

}
