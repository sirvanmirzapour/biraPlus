package com.sirvan.ltenfinal.Model;

import android.widget.EditText;

public class ContactListModel {
    String Name;

    public ContactListModel() {
    }

    public ContactListModel(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}