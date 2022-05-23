package com.sirvan.ltenfinal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sirvan.ltenfinal.App.G;
import com.sirvan.ltenfinal.App.MyAlertDialog;
import com.sirvan.ltenfinal.App.SMSUtils;
import com.sirvan.ltenfinal.R;

public class ActivityUSSD extends AppCompatActivity {

    private String strNumber, strPassword, strName;
    private TextView txtUSSDTitle, txtUSSDParagraph;
    private EditText edtUSSD;
    private Button btnUSSD, btnUSSDCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ussd);


        strNumber = getIntent().getExtras().getString("number");
        strPassword = getIntent().getExtras().getString("password");
        strName = getIntent().getExtras().getString("name");
        Ini();
        Click();
    }

    private void Ini() {
        txtUSSDParagraph = findViewById(R.id.txtUSSDParagraph);
        txtUSSDTitle = findViewById(R.id.txtUSSDTitle);
        edtUSSD = findViewById(R.id.edtUSSD);
        btnUSSD = findViewById(R.id.btnUSSD);
        btnUSSDCancel = findViewById(R.id.btnUSSDCancel);
        btnUSSDCancel.setTypeface(G.face);
        btnUSSD.setTypeface(G.face);

        txtUSSDTitle.setTypeface(G.faceBold);
        txtUSSDTitle.setText("اجرا USSD بر روی دستگاه " + strName);
        txtUSSDParagraph.setTypeface(G.face);
    }

    private void Click() {
        btnUSSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtUSSD.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "فیلد USSD نباید خالی باشد...", Toast.LENGTH_LONG).show();
                } else {
                    SMSUtils.sendSMS(getApplicationContext(), strNumber, "*#" + strPassword + "*07*0*" + edtUSSD.getText().toString() + "#");
                    finish();
                }

            }
        });

        btnUSSDCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
