package com.sirvan.ltenfinal.Model;

public class DeviceListModel {
    private int function;
    private String name;
    private String number;
    private String pass;

    public int getFunction() {
        return function;
    }

    public void setFunction(int function) {
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public DeviceListModel(int function, String name, String number, String pass) {
        this.function = function;
        this.name = name;
        this.number = number;
        this.pass = pass;
    }
}