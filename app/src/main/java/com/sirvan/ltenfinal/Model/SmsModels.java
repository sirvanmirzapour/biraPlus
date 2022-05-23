package com.sirvan.ltenfinal.Model;

import com.sirvan.ltenfinal.R;

public class SmsModels {
    private String time;
    private String date;
    private String msg;
    private int layoutID = R.layout.rv_sms_inbox;
    private int image;

    public SmsModels() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public SmsModels(String time, String date, String msg, int layoutID, int image) {
        this.time = time;
        this.date = date;
        this.msg = msg;
        this.layoutID = layoutID;
        this.image = image;
    }
}
