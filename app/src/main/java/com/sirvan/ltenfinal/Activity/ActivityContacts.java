package com.sirvan.ltenfinal.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sirvan.ltenfinal.Adapter.ContactsAdapter;
import com.sirvan.ltenfinal.Adapter.DeviceListAdapter;
import com.sirvan.ltenfinal.App.G;
import com.sirvan.ltenfinal.App.MyAlertDialog;
import com.sirvan.ltenfinal.App.SMSUtils;
import com.sirvan.ltenfinal.Model.ContactListModel;
import com.sirvan.ltenfinal.Model.DeviceListModel;
import com.sirvan.ltenfinal.R;

import java.util.ArrayList;

public class ActivityContacts extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String strNumber, strPassword,strName;
    private CardView cvGetAllContacts;
    private TextView txtGetAllUser,txtToolBarContacts;

    private MyAlertDialog myAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        myAlertDialog = new MyAlertDialog(ActivityContacts.this,getApplicationContext());


        Toolbar toolbar = findViewById(R.id.toolBarContacts);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        strNumber = getIntent().getExtras().getString("number");
        strPassword = getIntent().getExtras().getString("password");
        strName = getIntent().getExtras().getString("name");
        Ini();
        Click();
        buildRecyclerView();

        txtToolBarContacts.setText("مخاطبین "+strName);
    }

    private void Ini() {
        recyclerView = findViewById(R.id.rvContacts);
        cvGetAllContacts = findViewById(R.id.cvGetAllContacts);
        txtGetAllUser = findViewById(R.id.txtGetAllUser);
        txtToolBarContacts = findViewById(R.id.txtToolBarContacts);

        txtGetAllUser.setTypeface(G.face);
        txtToolBarContacts.setTypeface(G.faceBold);
    }

    private void buildRecyclerView() {

        ContactsAdapter contactsAdapter;
        LinearLayoutManager llmVertical = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llmVertical);
        recyclerView.setHasFixedSize(true);
        contactsAdapter = new ContactsAdapter(this,ActivityContacts.this, loadData(), strNumber, strPassword);
        recyclerView.setAdapter(contactsAdapter);
    }

    private ArrayList<ContactListModel> loadData() {
        ArrayList<ContactListModel> model = new ArrayList<>();
        model.add(new ContactListModel("کاربر یکم"));
        model.add(new ContactListModel("کاربر دوم"));
        model.add(new ContactListModel("کاربر سوم"));
        model.add(new ContactListModel("کاربر چهارم"));
        model.add(new ContactListModel("کاربر پنجم"));
        model.add(new ContactListModel("کاربر ششم"));
        model.add(new ContactListModel("کاربر هفتم"));
        model.add(new ContactListModel("کاربر هشتم"));
        model.add(new ContactListModel("کاربر نهم"));
        model.add(new ContactListModel("کاربر دهم"));
        return model;
    }

    private void Click() {
        cvGetAllContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAlertDialog.Show("آیا میخواهید لیست مخاطبین ثبت شده رو دستگاه را دریافت کنید؟","*71#",strNumber,strPassword);

            }
        });
    }

}
