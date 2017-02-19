package com.example.Semmiverian.myapplication.backend;

import java.util.ArrayList;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    private ArrayList<String> listData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }

    public ArrayList<String> getListData() {
        return listData;
    }

    public void setListData(ArrayList<String> listData) {
        this.listData = listData;
    }
}