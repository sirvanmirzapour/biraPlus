package com.sirvan.ltenfinal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sirvan.ltenfinal.Adapter.DeviceListAdapter;
import com.sirvan.ltenfinal.App.G;
import com.sirvan.ltenfinal.App.Prefs;
import com.sirvan.ltenfinal.Model.DeviceListModel;
import com.sirvan.ltenfinal.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

public class ActivityGetNumber extends AppCompatActivity {

    private Prefs prefs;
    private LinearLayout llSaveInGetNumber;
    private EditText edtPassOfDevice, edtNumberOfDevice, edtNameOfDevice;
    private CardView cvFieldsForGetData, cvAddDevice;
    private RecyclerView rvDeviceList;
    private TextView txtToolBarGetNumber;

    private int function = 5 ;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_number);

        Toolbar toolbar = findViewById(R.id.toolbarGetNumber);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Ini();
        Click();
        ClickAddDevice();
        if (loadData() != null) {
            buildRecyclerView(loadData());
        }
        radioButton();

    }

    private void Ini() {
        prefs = new Prefs(this);
        llSaveInGetNumber = findViewById(R.id.llSaveInGetNumber);
        edtPassOfDevice = findViewById(R.id.edtPassOfDevice);
        edtNumberOfDevice = findViewById(R.id.edtNumberOfDevice);
        edtNameOfDevice = findViewById(R.id.edtNameOfDevice);

        cvAddDevice = findViewById(R.id.cvAddDevice);
        cvFieldsForGetData = findViewById(R.id.cvFieldsForGetData);

        rvDeviceList = findViewById(R.id.rvDeviceList);
        txtToolBarGetNumber = findViewById(R.id.txtToolBarGetNumber);
        txtToolBarGetNumber.setTypeface(G.face);

    }

    private void Click() {
        llSaveInGetNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeData(getData());
            }
        });
    }

    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void ClickAddDevice() {
        cvAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // YoYo.with(Techniques.SlideInUp)
                  //      .duration(400)
                 //       .playOn(cvFieldsForGetData);
           //     cvAddDevice.setVisibility(View.GONE);
             //   cvFieldsForGetData.setVisibility(View.VISIBLE);
            }
        });
    }

    private void buildRecyclerView(ArrayList<DeviceListModel> model) {
        DeviceListAdapter deviceListAdapter;
        LinearLayoutManager llmVertical = new LinearLayoutManager(this);
        llmVertical.setReverseLayout(true);
        rvDeviceList.setLayoutManager(llmVertical);
        rvDeviceList.setHasFixedSize(true);
        deviceListAdapter = new DeviceListAdapter(this, model, new DeviceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DeviceListModel item) {
                writeData(deleteData(item.getNumber()));
            }
        });
        rvDeviceList.setAdapter(deviceListAdapter);
    }

    private void writeData(ArrayList<DeviceListModel> model) {
        SharedPreferences sharedPreferences = getSharedPreferences("listDevice", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        Gson gson = new Gson();
        String json = gson.toJson(model);
        editor.putString("list", json);
        editor.apply();
        buildRecyclerView(model);
    }

    private ArrayList<DeviceListModel> loadData() {

        ArrayList<DeviceListModel> model;
        SharedPreferences sharedPreferences = getSharedPreferences("listDevice", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<DeviceListModel>>() {
        }.getType();
        model = gson.fromJson(json, type);
        if (model != null) {
            return model;
        } else {
            return null;
        }
    }

    private ArrayList<DeviceListModel> getData() {

        String strNameOfDevice = edtNameOfDevice.getText().toString();
        String strNumberOfDevice = edtNumberOfDevice.getText().toString();
        String strPassOfDevice = edtPassOfDevice.getText().toString();


        ArrayList<DeviceListModel> model;
        if (loadData() != null) {
            model = new ArrayList<>(loadData());
        } else {
            model = new ArrayList<>();
        }

        if (strNumberOfDevice.length() == 11 && isNumeric(strNumberOfDevice)) {
            if (strPassOfDevice.length() == 4 && isNumeric(strPassOfDevice)) {
                if(function != 5) {
                    model.add(new DeviceListModel(function, strNameOfDevice, strNumberOfDevice, strPassOfDevice));

                    Toast.makeText(getApplicationContext(), "شماره ثبت شد", Toast.LENGTH_LONG).show();
                //    YoYo.with(Techniques.SlideInUp)
                //            .duration(400)
                //            .playOn(cvAddDevice);
                //    cvAddDevice.setVisibility(View.VISIBLE);
                //    cvFieldsForGetData.setVisibility(View.GONE);

                    edtNameOfDevice.setText("");
                    edtNumberOfDevice.setText("");
                    edtPassOfDevice.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "لطفا نوع کارکرد دستگاه را انتخاب کنید", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "رمز وارد شده صحیح نیست", Toast.LENGTH_LONG).show();
                edtPassOfDevice.setText("");
            }

        } else {
            Toast.makeText(getApplicationContext(), "شماره موبایل صحیح نیست", Toast.LENGTH_LONG).show();
            edtNumberOfDevice.setText("");
        }

        return model;
    }

    private ArrayList<DeviceListModel> deleteData(String number) {

        ArrayList<DeviceListModel> model = new ArrayList<>(loadData());

        for (int i = 0; i < model.size(); i++) {
            if (number.equals(model.get(i).getNumber())) {
                model.remove(i);
            }
        }
        return model;
    }

   private void radioButton(){
       radioGroup =  findViewById(R.id.radioFunction);

       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
       {
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch(checkedId){
                   case R.id.radioOne:
                      function = 1;
                       break;
                   case R.id.radioTwo:
                       function = 2;
                       break;
                   case R.id.radioThree:
                       function = 3;
                       break;
                   case R.id.radioFour:
                       function = 4;
                       break;
               }
           }
       });

   }

    @Override
    public void onBackPressed() {
        if (loadData() == null || loadData().size() == 0) {
            Toast.makeText(getApplicationContext(), "هیچ دستگاه ثبت شده وجود ندارد", Toast.LENGTH_LONG).show();
            finish();
       // }else if(cvFieldsForGetData.getVisibility() == View.VISIBLE){
          //  YoYo.with(Techniques.SlideInUp)
           //         .duration(400)
          //          .playOn(cvAddDevice);
        //    cvAddDevice.setVisibility(View.VISIBLE);
        //    cvFieldsForGetData.setVisibility(View.GONE);
      //  } else {

        }
        super.onBackPressed();
    }
}
